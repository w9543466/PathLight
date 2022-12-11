package uk.ac.tees.w9543466.pathlight.auth;

import static uk.ac.tees.w9543466.pathlight.utils.TextUtil.isValid;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Date;

import uk.ac.tees.w9543466.pathlight.BlankResponse;
import uk.ac.tees.w9543466.pathlight.auth.models.WorkerSignupRequest;
import uk.ac.tees.w9543466.pathlight.utils.PrefUtil;
import uk.ac.tees.w9543466.pathlight.utils.TimeFormatterUtil;

public class WorkerSignupViewModel extends AndroidViewModel {

    public final ObservableField<String> firstName = new ObservableField<>("");
    public final ObservableField<String> lastName = new ObservableField<>("");
    public final ObservableField<String> email = new ObservableField<>("");
    public final ObservableField<String> password = new ObservableField<>("");
    public final ObservableField<String> jobTitle = new ObservableField<>("");
    public final ObservableField<String> dob = new ObservableField<>("");
    public final ObservableField<String> languages = new ObservableField<>("");
    public final ObservableField<String> skills = new ObservableField<>("");

    public final ObservableField<Boolean> loginProgress = new ObservableField<>(false);
    public final ObservableField<Boolean> loginEnabled = new ObservableField<>(true);

    private final AuthRepo authRepo = new AuthRepo();
    private final PrefUtil prefUtil;
    private final MutableLiveData<BlankResponse> signupResponse = new MutableLiveData<>();

    public WorkerSignupViewModel(@NonNull Application application) {
        super(application);
        prefUtil = new PrefUtil(application);
    }


    public LiveData<BlankResponse> getSignupResponse() {
        return signupResponse;
    }

    public void doSignUp() {
        WorkerSignupRequest request = new WorkerSignupRequest();
        String email = this.email.get();
        String pwd = password.get();
        String firstName = this.firstName.get();
        String lastName = this.lastName.get();
        String jobTitle = this.jobTitle.get();
        String dob = this.dob.get();
        String languages = this.languages.get();
        String skills = this.skills.get();

        if (!isValid(email, pwd, firstName, lastName, jobTitle, dob, languages, skills)) {
            BlankResponse response = new BlankResponse();
            response.setSuccess(false);
            response.setMessage("All fields are required");
            signupResponse.postValue(response);
            return;
        }
        loginProgress.set(true);
        loginEnabled.set(false);
        request.setEmail(email);
        request.setPassword(pwd);
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setJobTitle(jobTitle);
        request.setDob(dob);
        request.setLanguages(languages);
        request.setSkills(skills);

        authRepo.signupWorker(request, response -> {
            loginProgress.set(false);
            loginEnabled.set(true);
            if (response.isSuccess()) {
                prefUtil.saveLoginInfo(email, pwd, UserRole.WORKER.getRole());
            }
            signupResponse.postValue(response);
        });
    }

    public void onDobSelected(Date time) {
        String formatted = TimeFormatterUtil.format(time.getTime(), "dd/MMM/YYYY");
        dob.set(formatted);
    }
}
