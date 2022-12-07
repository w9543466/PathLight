package uk.ac.tees.w9543466.pathlight.employer.works;

import static uk.ac.tees.w9543466.pathlight.utils.TextUtil.isValid;

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
import uk.ac.tees.w9543466.pathlight.employer.EmployerRepo;
import uk.ac.tees.w9543466.pathlight.utils.TimeFormatterUtil;

public class WorkViewModel extends AndroidViewModel {

    private final EmployerRepo employerRepo;
    private final Gson gson = new Gson();

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

    public void createWork() {
        WorkRequest request = new WorkRequest();
        String titleValue = this.title.get();
        String skillsValue = skills.get();
        String amountValue = amount.get();
        String startTimeValue = startTime.get();
        if (isValid(titleValue, skillsValue, amountValue, startTimeValue) && location != null) {
            request.setTitle(titleValue);
            request.setSkills(skillsValue);
            request.setTotalRate(Double.parseDouble(amountValue));
            request.setLat(location.getLatitude());
            request.setLng(location.getLongitude());
            request.setStartTime(TimeFormatterUtil.toMillis(startTimeValue));
            proceedEnabled.set(false);
            progress.set(true);
            employerRepo.createWork(request, response -> {
                proceedEnabled.set(true);
                progress.set(false);
                createWorkLiveData.postValue(response);
            });
        } else {
            BlankResponse value = new BlankResponse();
            value.setSuccess(false);
            value.setMessage("All fields are required");
            createWorkLiveData.postValue(value);
        }
    }

    public LiveData<BlankResponse> getCreateWorkLiveData() {
        return createWorkLiveData;
    }
}