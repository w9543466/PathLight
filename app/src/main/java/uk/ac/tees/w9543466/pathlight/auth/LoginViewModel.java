package uk.ac.tees.w9543466.pathlight.auth;

import android.app.Application;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import uk.ac.tees.w9543466.pathlight.utils.PrefUtil;

public class LoginViewModel extends AndroidViewModel {

    private final AuthRepo authRepo = new AuthRepo();
    private final ObservableField<String> emailId = new ObservableField<>("");
    private final ObservableField<String> password = new ObservableField<>("");
    private final ObservableField<UserRole> role = new ObservableField<>(UserRole.WORKER);
    private final ObservableField<Boolean> loginProgress = new ObservableField<>(false);
    private final ObservableField<Boolean> loginEnabled = new ObservableField<>(true);
    private final ObservableField<String> loginError = new ObservableField<>("");

    private final PrefUtil prefUtil;
    private final MutableLiveData<LoginResponse> loginLiveData = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        prefUtil = new PrefUtil(application);
    }

    public ObservableField<Boolean> getLoginEnabled() {
        return loginEnabled;
    }

    public ObservableField<String> getLoginError() {
        return loginError;
    }

    public ObservableField<Boolean> getLoginProgress() {
        return loginProgress;
    }

    public ObservableField<String> getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId.set(emailId);
    }

    public ObservableField<String> getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public LiveData<LoginResponse> getLoginLiveData() {
        return loginLiveData;
    }

    public void doLogin() {
        loginEnabled.set(false);
        loginError.set("");
        loginProgress.set(true);
        if (!validateLoginForm()) return;
        String role = this.role.get().getRole();
        authRepo.login(emailId.get(), password.get(), role, response -> {
            loginProgress.set(false);
            loginEnabled.set(true);
            if (response.isSuccess()) {
                loginLiveData.postValue(response);
                prefUtil.saveLoginInfo(emailId.get(), password.get(), role);
            } else {
                loginError.set(response.getMessage());
            }
        });
    }

    private boolean validateLoginForm() {
        LoginResponse failResp = new LoginResponse();
        failResp.setSuccess(false);
        boolean emailEmpty = emailId.get().isEmpty();
        boolean pwdEmpty = password.get().isEmpty();
        if (emailEmpty) loginError.set("Please enter emailId");
        if (pwdEmpty) loginError.set("Please enter password");

        if (emailEmpty || pwdEmpty) {
            loginLiveData.postValue(failResp);
            loginProgress.set(false);
            loginEnabled.set(true);
            return false;
        }
        return true;
    }

    public void onRoleChanged(CompoundButton buttonView, boolean isChecked) {
        role.set(isChecked ? UserRole.EMPLOYER : UserRole.WORKER);
    }
}
