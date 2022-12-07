package uk.ac.tees.w9543466.pathlight.employer.applications;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import uk.ac.tees.w9543466.pathlight.DataStatus;
import uk.ac.tees.w9543466.pathlight.employer.EmployerRepo;

public class ApplicationsViewModel extends AndroidViewModel {
    private long selectedWorkId = -1;
    private final EmployerRepo employerRepo;
    private final MutableLiveData<List<WorkApplication>> applicationLiveData = new MutableLiveData<>();
    public final ObservableField<DataStatus> applicationDataStatus = new ObservableField<>(DataStatus.LOADING);

    public ApplicationsViewModel(@NonNull Application application) {
        super(application);
        employerRepo = new EmployerRepo(application);
    }

    public LiveData<List<WorkApplication>> getApplicationLiveData() {
        return applicationLiveData;
    }

    public void setSelectedWorkId(long selectedWorkId) {
        this.selectedWorkId = selectedWorkId;
        getApplications();
    }

    private void getApplications() {
        if (selectedWorkId == -1) {
            applicationDataStatus.set(DataStatus.ERROR);
            return;
        }
        applicationDataStatus.set(DataStatus.LOADING);
        employerRepo.getApplications(selectedWorkId, response -> {
            if (response.isSuccess()) {
                List<WorkApplication> applications = response.getApplications();
                if (applications == null || applications.isEmpty()) {
                    applicationDataStatus.set(DataStatus.EMPTY);
                } else {
                    applicationLiveData.postValue(applications);
                    applicationDataStatus.set(DataStatus.SUCCESS);
                }
            } else {
                applicationDataStatus.set(DataStatus.ERROR);
            }
        });
    }
}
