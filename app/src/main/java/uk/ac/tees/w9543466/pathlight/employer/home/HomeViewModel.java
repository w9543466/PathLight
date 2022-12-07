package uk.ac.tees.w9543466.pathlight.employer.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import uk.ac.tees.w9543466.pathlight.DataStatus;
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
    private long selectedWorkId = -1;
    public ObservableField<DataStatus> workDataStatus = new ObservableField<>(DataStatus.LOADING);

    public HomeViewModel(@NonNull Application application) {
        super(application);
        employerRepo = new EmployerRepo(application);
        PathLightDatabase database = PathLightDatabase.getDatabase(application);
        workDao = database.workDao();
        getWorks();
    }

    private void getWorks() {
        employerRepo.getWorks(response -> {
            if (response.isSuccess()) {
                List<WorkItem> works = response.getWorks();
                if (works == null || works.isEmpty()) {
                    workDataStatus.set(DataStatus.EMPTY);
                } else {
                    workLiveData.postValue(works);
                    saveToDb(works);
                    workDataStatus.set(DataStatus.SUCCESS);
                }
            } else {
                workDataStatus.set(DataStatus.ERROR);
            }
        });
    }

    private void saveToDb(List<WorkItem> works) {
        WorkEntity[] data = gson.fromJson(gson.toJson(works), WorkEntity[].class);
        ThreadWorker.execute(() -> workDao.insert(Arrays.asList(data)));
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
        return workItem.getWorkId();
    }

    public void onItemSelected(long workId) {
        selectedWorkId = workId;
        ThreadWorker.execute(() -> {
            WorkEntity data = workDao.get(workId);
            WorkItem item = gson.fromJson(gson.toJson(data), WorkItem.class);
            selectedWork.postValue(item);
        });
    }

    public long getSelectedWorkId() {
        return selectedWorkId;
    }

    public void deleteSelectedWork() {
        //TODO
    }
}