package uk.ac.tees.w9543466.pathlight.employer.works;

import static uk.ac.tees.w9543466.pathlight.utils.TextUtil.isValid;
import static uk.ac.tees.w9543466.pathlight.utils.TimeFormatterUtil.format;

import android.app.Application;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.util.Date;

import uk.ac.tees.w9543466.pathlight.BlankResponse;
import uk.ac.tees.w9543466.pathlight.db.PathLightDatabase;
import uk.ac.tees.w9543466.pathlight.db.WorkDao;
import uk.ac.tees.w9543466.pathlight.db.WorkEntity;
import uk.ac.tees.w9543466.pathlight.employer.EmployerRepo;
import uk.ac.tees.w9543466.pathlight.utils.ThreadWorker;
import uk.ac.tees.w9543466.pathlight.utils.TimeFormatterUtil;

public class WorkViewModel extends AndroidViewModel {

    private final EmployerRepo employerRepo;
    private final WorkDao workDao;
    private long selectedWorkId;

    public ObservableBoolean isEditMode = new ObservableBoolean();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> skills = new ObservableField<>();
    public ObservableField<String> amount = new ObservableField<>();
    public ObservableField<String> startTime = new ObservableField<>();
    public ObservableField<String> locationInfo = new ObservableField<>("Capturing current location...");
    public ObservableBoolean proceedEnabled = new ObservableBoolean(false);
    public ObservableBoolean progress = new ObservableBoolean(false);
    private Location location;
    private final MutableLiveData<BlankResponse> createWorkLiveData = new MutableLiveData<>();

    public WorkViewModel(@NonNull Application application) {
        super(application);
        employerRepo = new EmployerRepo(application);
        PathLightDatabase database = PathLightDatabase.getDatabase(application);
        workDao = database.workDao();
    }

    public void onStartTimeSelected(Date time) {
        startTime.set(time.toString());
    }

    public void onLocationObtained(Location location) {
        if (location == null) {
            //TODO
            return;
        }
        locationInfo.set("Location captured successfully");
        proceedEnabled.set(true);
        this.location = location;
    }

    public void onFormSubmitted() {
        WorkRequest request = getWorkRequest();
        if (isEditMode.get()) callUpdateWorkApi(request);
        else callCreateWorkApi(request);
    }

    private WorkRequest getWorkRequest() {
        WorkRequest request = new WorkRequest();
        String titleValue = this.title.get();
        String skillsValue = skills.get();
        String amountValue = amount.get();
        String startTimeValue = startTime.get();
        if (!isValid(titleValue, skillsValue, amountValue, startTimeValue) && location != null) {
            BlankResponse value = new BlankResponse();
            value.setSuccess(false);
            value.setMessage("All fields are required");
            createWorkLiveData.postValue(value);
            return null;
        }
        if (selectedWorkId != -1) {
            request.setId(selectedWorkId);
        }
        request.setTitle(titleValue);
        request.setSkills(skillsValue);
        request.setTotalRate(Double.parseDouble(amountValue));
        request.setLat(location.getLatitude());
        request.setLng(location.getLongitude());
        request.setStartTime(TimeFormatterUtil.toMillis(startTimeValue));
        proceedEnabled.set(false);
        progress.set(true);
        return request;
    }

    private void callUpdateWorkApi(WorkRequest request) {
        employerRepo.updateWork(request, this::onWorkCreated);
    }

    private void callCreateWorkApi(WorkRequest request) {
        employerRepo.createWork(request, this::onWorkCreated);
    }

    private void onWorkCreated(BlankResponse response) {
        proceedEnabled.set(true);
        progress.set(false);
        createWorkLiveData.postValue(response);
    }

    public LiveData<BlankResponse> getCreateWorkLiveData() {
        return createWorkLiveData;
    }

    public void setEditMode(boolean isEditMode) {
        this.isEditMode.set(isEditMode);
        updateForm();
    }

    private void updateForm(WorkItem item) {
        if(item == null) return;
        title.set(item.getTitle());
        skills.set(item.getSkills());
        amount.set(item.getTotalRate() + "");
        startTime.set(format(item.getStartTime()));
    }

    private void updateForm() {
        if (!isEditMode.get() || selectedWorkId == -1) {
            return;
        }
        ThreadWorker.execute(() -> {
            Gson gson = new Gson();
            WorkEntity data = workDao.get(selectedWorkId);
            WorkItem item = gson.fromJson(gson.toJson(data), WorkItem.class);
            updateForm(item);
        });

    }


    public void setSelectedWorkId(long selectedWorkId) {
        this.selectedWorkId = selectedWorkId;
        updateForm();
    }
}