package uk.ac.tees.w9543466.pathlight.employer.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uk.ac.tees.w9543466.pathlight.BaseResponse;
import uk.ac.tees.w9543466.pathlight.DataStatus;
import uk.ac.tees.w9543466.pathlight.WorkResponse;
import uk.ac.tees.w9543466.pathlight.db.PathLightDatabase;
import uk.ac.tees.w9543466.pathlight.db.WorkDao;
import uk.ac.tees.w9543466.pathlight.db.WorkEntity;
import uk.ac.tees.w9543466.pathlight.employer.EmployerRepo;
import uk.ac.tees.w9543466.pathlight.employer.works.WorkItem;
import uk.ac.tees.w9543466.pathlight.utils.ThreadWorker;

public class HomeViewModel extends AndroidViewModel {

    private final EmployerRepo employerRepo;
    private final WorkDao workDao;
    private final Gson gson = new Gson();

    private final MutableLiveData<List<WorkItem>> workLiveData = new MutableLiveData<>();
    private final MutableLiveData<WorkItem> selectedWork = new MutableLiveData<>();
    private final MutableLiveData<BaseResponse> deleteWorkLiveData = new MutableLiveData<>();
    private long selectedWorkId = -1;
    public ObservableField<DataStatus> workDataStatus = new ObservableField<>(DataStatus.LOADING);
    public ObservableBoolean progressVisible = new ObservableBoolean(false);

    public HomeViewModel(@NonNull Application application) {
        super(application);
        employerRepo = new EmployerRepo(application);
        PathLightDatabase database = PathLightDatabase.getDatabase(application);
        workDao = database.workDao();
        getWorks();
    }

    private void getWorks() {
        employerRepo.getWorks(this::onWorkListResponse);
    }

    private void onWorkListResponse(WorkResponse response) {
        ThreadWorker.execute(() -> {
            if (response.isSuccess()) {
                workDao.deleteAll();
                List<WorkItem> works = response.getWorks();
                if (works == null || works.isEmpty()) {
                    works = new ArrayList<>();
                    workDataStatus.set(DataStatus.EMPTY);
                } else {
                    WorkEntity[] data = gson.fromJson(gson.toJson(works), WorkEntity[].class);
                    workDao.insert(Arrays.asList(data));
                    workDataStatus.set(DataStatus.SUCCESS);
                }
                workLiveData.postValue(works);
            } else {
                workDataStatus.set(DataStatus.ERROR);
            }
        });
    }

    public LiveData<List<WorkItem>> getWorkLiveData() {
        return workLiveData;
    }

    public LiveData<WorkItem> getSelectedWork() {
        return selectedWork;
    }

    public void retryWorks() {
        workDataStatus.set(DataStatus.LOADING);
        getWorks();
    }

    public long getItemWorkId(int position) {
        WorkItem workItem = workLiveData.getValue().get(position);
        return workItem.getId();
    }

    public void onItemSelected(long workId) {
        selectedWorkId = workId;
        ThreadWorker.execute(() -> {
            WorkEntity data = workDao.get(workId);
            WorkItem item = gson.fromJson(gson.toJson(data), WorkItem.class);
            selectedWork.postValue(item);
        });
    }

    public LiveData<BaseResponse> getDeleteWorkLiveData() {
        return deleteWorkLiveData;
    }

    public long getSelectedWorkId() {
        return selectedWorkId;
    }

    public void deleteSelectedWork() {
        progressVisible.set(true);
        employerRepo.deleteWork(selectedWorkId, this::onDeleteWorkResponse);
    }

    private void onDeleteWorkResponse(WorkResponse response) {
        ThreadWorker.execute(() -> {
            progressVisible.set(false);
            if (response.isSuccess()) {
                workDao.deleteAll();
                List<WorkItem> works = response.getWorks();
                if (works != null && !works.isEmpty()) {
                    WorkEntity[] data = gson.fromJson(gson.toJson(works), WorkEntity[].class);
                    workDao.insert(Arrays.asList(data));
                }
            }
            List<WorkEntity> all = workDao.getAll();
            deleteWorkLiveData.postValue(response);
        });
    }
}