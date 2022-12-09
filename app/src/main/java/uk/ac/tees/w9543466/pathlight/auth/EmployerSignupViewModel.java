package uk.ac.tees.w9543466.pathlight.auth;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import uk.ac.tees.w9543466.pathlight.BlankResponse;
import uk.ac.tees.w9543466.pathlight.auth.models.EmployerSignupRequest;
import uk.ac.tees.w9543466.pathlight.utils.PrefUtil;

public class EmployerSignupViewModel extends AndroidViewModel {

    public final ObservableField<String> firstName = new ObservableField<>("");
    public final ObservableField<String> lastName = new ObservableField<>("");
    public final ObservableField<String> email = new ObservableField<>("");
    public final ObservableField<String> password = new ObservableField<>("");
    public final ObservableField<String> phone = new ObservableField<>("");

    public final ObservableField<Boolean> loginProgress = new ObservableField<>(false);
    public final ObservableField<Boolean> loginEnabled = new ObservableField<>(true);

    private final AuthRepo authRepo = new AuthRepo();
    private final PrefUtil prefUtil;
    private final MutableLiveData<BlankResponse> signupResponse = new MutableLiveData<>();

    public EmployerSignupViewModel(@NonNull Application application) {
        super(application);
        prefUtil = new PrefUtil(application);
    }

    public LiveData<BlankResponse> getSignupResponse() {
        return signupResponse;
    }

    public void doSignUp() {
        loginProgress.set(true);
        loginEnabled.set(false);

        EmployerSignupRequest request = new EmployerSignupRequest();
        String email = this.email.get();
        String pwd = password.get();
        String firstName = this.firstName.get();
        String lastName = this.lastName.get();
        String phone = this.phone.get();

        request.setEmail(email);
        request.setPassword(pwd);
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setPhone(phone);

        authRepo.signupEmployer(request, response -> {
            loginProgress.set(false);
            loginEnabled.set(true);
            if (response.isSuccess()) {
                prefUtil.saveLoginInfo(email, pwd, UserRole.WORKER.getRole());
                signupResponse.postValue(response);
            }
        });
    }
}
