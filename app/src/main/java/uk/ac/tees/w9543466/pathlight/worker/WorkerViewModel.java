package uk.ac.tees.w9543466.pathlight.worker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.stream.Collectors;

import uk.ac.tees.w9543466.pathlight.DataStatus;
import uk.ac.tees.w9543466.pathlight.WorkStatus;
import uk.ac.tees.w9543466.pathlight.employer.works.WorkItem;
import uk.ac.tees.w9543466.pathlight.worker.apimodel.WorkerApplicationItem;

public class WorkerViewModel extends AndroidViewModel {
    private WorkerRepo workerRepo;
    public ObservableField<DataStatus> workDataStatus = new ObservableField<>(DataStatus.LOADING);
    private final MutableLiveData<List<WorkDto>> applicationsList = new MutableLiveData<>();
    private final MutableLiveData<List<WorkDto>> workLiveData = new MutableLiveData<>();

    public WorkerViewModel(@NonNull Application application) {
        super(application);
        workerRepo = new WorkerRepo(application);
        getAllWorks();
    }

    public LiveData<List<WorkDto>> getWorkLiveData() {
        return workLiveData;
    }

    public LiveData<List<WorkDto>> getApplicationsList() {
        return applicationsList;
    }


    public void onApplicationSubmitted(int position) {

    }

    private void getAllWorks() {
        workerRepo.getWorks(response -> {
            if (response.isSuccess()) {
                List<WorkItem> works = response.getWorks();
                if (works == null || works.isEmpty()) {
                    workDataStatus.set(DataStatus.EMPTY);
                } else {
                    List<WorkDto> converted = works.stream().map(work -> {
                        WorkStatus workStatus = work.getWorkStatus();
                        boolean applied = work.isApplied();
                        boolean workNotStarted = workStatus == WorkStatus.NOT_STARTED;
                        WorkDto dto = new WorkDto();
                        dto.setTitle(work.getTitle());
                        dto.setAlreadyApplied(applied);
                        dto.setApplyEnabled(workNotStarted && !applied);
                        dto.setId(work.getWorkId());
                        dto.setStartTime(work.getStartTime());
                        dto.setApplyInProgress(false);
                        dto.setTotalRate(work.getTotalRate());
                        return dto;
                    }).collect(Collectors.toList());
                    workLiveData.postValue(converted);
                    workDataStatus.set(DataStatus.SUCCESS);
                }
            } else {
                workDataStatus.set(DataStatus.ERROR);
            }
        });
    }

    private void getWorkApplications() {
        workerRepo.getApplications(response -> {
            if (response.isSuccess()) {
                List<WorkerApplicationItem> works = response.getWorks();
                if (works == null || works.isEmpty()) {
                    workDataStatus.set(DataStatus.EMPTY);
                } else {

                    //TODO
                    //applicationsList.postValue(result);
                    workDataStatus.set(DataStatus.SUCCESS);
                }
            } else {
                workDataStatus.set(DataStatus.ERROR);
            }
        });
    }
}
