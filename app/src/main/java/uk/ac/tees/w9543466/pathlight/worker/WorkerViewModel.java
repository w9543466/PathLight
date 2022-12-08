package uk.ac.tees.w9543466.pathlight.worker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import uk.ac.tees.w9543466.pathlight.DataStatus;
import uk.ac.tees.w9543466.pathlight.WorkStatus;
import uk.ac.tees.w9543466.pathlight.employer.works.WorkItem;
import uk.ac.tees.w9543466.pathlight.worker.apimodel.ApplyWorkRequest;
import uk.ac.tees.w9543466.pathlight.worker.apimodel.WorkerApplicationItem;

public class WorkerViewModel extends AndroidViewModel {

    private final WorkerRepo workerRepo;
    public ObservableField<DataStatus> workDataStatus = new ObservableField<>(DataStatus.LOADING);
    public ObservableField<DataStatus> applicationDataStatus = new ObservableField<>(DataStatus.LOADING);
    private final MutableLiveData<List<WorkerApplicationItem>> applicationsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<WorkDto>> workLiveData = new MutableLiveData<>();
    private final List<WorkDto> workData = new ArrayList<>();

    public WorkerViewModel(@NonNull Application application) {
        super(application);
        workerRepo = new WorkerRepo(application);
        getAllWorks();
    }

    public LiveData<List<WorkDto>> getWorkLiveData() {
        return workLiveData;
    }

    public LiveData<List<WorkerApplicationItem>> getApplicationsLiveData() {
        return applicationsLiveData;
    }

    public void onApplicationSubmitted(int position, double proposedRate) {
        WorkDto work = getWorkData(position);
        work.setApplyInProgress(true);
        work.setApplyEnabled(false);
        ApplyWorkRequest request = new ApplyWorkRequest();
        request.setWorkId(work.getId());
        request.setProposedRate(proposedRate);
        workerRepo.apply(request, response -> {
            work.setApplyInProgress(false);
            if (response.isSuccess()) {
                work.setAlreadyApplied(true);
                work.setApplyEnabled(false);
            } else {
                work.setApplyEnabled(true);
            }
        });
    }

    public WorkDto getWorkData(int pos) {
        return workData.get(pos);
    }

    public void getAllWorks() {
        workDataStatus.set(DataStatus.LOADING);
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
                        dto.setId(work.getId());
                        dto.setStartTime(work.getStartTime());
                        dto.setApplyInProgress(false);
                        dto.setTotalRate(work.getTotalRate());
                        return dto;
                    }).collect(Collectors.toList());
                    updateWorks(converted);
                    workDataStatus.set(DataStatus.SUCCESS);
                }
            } else {
                workDataStatus.set(DataStatus.ERROR);
            }
        });
    }

    private void updateWorks(List<WorkDto> works) {
        workData.clear();
        workData.addAll(works);
        workLiveData.postValue(workData);
    }

    public void getWorkApplications() {
        applicationDataStatus.set(DataStatus.LOADING);
        workerRepo.getApplications(response -> {
            if (response.isSuccess()) {
                List<WorkerApplicationItem> works = response.getApplications();
                if (works == null || works.isEmpty()) {
                    applicationDataStatus.set(DataStatus.EMPTY);
                } else {
                    applicationsLiveData.postValue(works);
                    applicationDataStatus.set(DataStatus.SUCCESS);
                }
            } else {
                applicationDataStatus.set(DataStatus.ERROR);
            }
        });
    }
}
