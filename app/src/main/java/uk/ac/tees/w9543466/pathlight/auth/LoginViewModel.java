package uk.ac.tees.w9543466.pathlight.auth;

import android.widget.CompoundButton;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private final AuthRepo authRepo = new AuthRepo();
    private final ObservableField<String> emailId = new ObservableField<>("");
    private final ObservableField<String> password = new ObservableField<>("");
    private final ObservableField<UserRole> role = new ObservableField<>(UserRole.WORKER);
    private final ObservableField<Boolean> loginProgress = new ObservableField<>(false);
    private final ObservableField<Boolean> loginEnabled = new ObservableField<>(true);
    private final ObservableField<String> loginError = new ObservableField<>("");

    final MutableLiveData<LoginResponse> loginLiveData = new MutableLiveData<>();

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

    public void doLogin() {
        loginEnabled.set(false);
        loginError.set("");
        loginProgress.set(true);
        if (!validateLoginForm()) return;
        authRepo.login(emailId.get(), password.get(), role.get().getRole(), response -> {
            loginProgress.set(false);
            if (!response.isSuccess()) {
                loginError.set(response.getMessage());
                loginEnabled.set(true);
            }
            loginLiveData.postValue(response);
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
