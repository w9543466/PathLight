package uk.ac.tees.w9543466.pathlight.auth;

import android.app.Application;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import uk.ac.tees.w9543466.pathlight.BlankResponse;
import uk.ac.tees.w9543466.pathlight.auth.models.LoginResponse;
import uk.ac.tees.w9543466.pathlight.utils.PrefUtil;

public class LoginViewModel extends AndroidViewModel {

    public final AuthRepo authRepo = new AuthRepo();
    public final ObservableField<String> emailId = new ObservableField<>("");
    public final ObservableField<String> password = new ObservableField<>("");
    public final ObservableField<UserRole> role = new ObservableField<>(UserRole.WORKER);
    public final ObservableBoolean loginProgress = new ObservableBoolean(false);
    public final ObservableBoolean loginEnabled = new ObservableBoolean(true);
    public final ObservableBoolean forgotPwdEnabled = new ObservableBoolean(true);
    public final ObservableField<String> loginError = new ObservableField<>("");

    private final PrefUtil prefUtil;
    private final MutableLiveData<LoginResponse> loginLiveData = new MutableLiveData<>();
    private final MutableLiveData<BlankResponse> forgotPwdLiveData = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        prefUtil = new PrefUtil(application);
    }

    public LiveData<LoginResponse> getLoginLiveData() {
        return loginLiveData;
    }

    public LiveData<BlankResponse> getForgotPwdLiveData() {
        return forgotPwdLiveData;
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
                response.setRole(UserRole.valueOf(role));
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

    public void onForgotPassword() {
        String email = emailId.get();
        boolean emailEmpty = email.isEmpty();
        if (emailEmpty) {
            BlankResponse value = new BlankResponse();
            value.setSuccess(false);
            value.setMessage("Please enter email id");
            forgotPwdLiveData.postValue(value);
            return;
        }
        forgotPwdEnabled.set(false);
        authRepo.forgotPassword(email, response -> {
            forgotPwdLiveData.postValue(response);
            forgotPwdEnabled.set(true);
        });
    }
}
