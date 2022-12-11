package uk.ac.tees.w9543466.pathlight.auth;


import uk.ac.tees.w9543466.pathlight.BlankResponse;
import uk.ac.tees.w9543466.pathlight.ResponseCallback;
import uk.ac.tees.w9543466.pathlight.auth.models.EmployerSignupRequest;
import uk.ac.tees.w9543466.pathlight.auth.models.LoginRequest;
import uk.ac.tees.w9543466.pathlight.auth.models.LoginResponse;
import uk.ac.tees.w9543466.pathlight.auth.models.WorkerSignupRequest;
import uk.ac.tees.w9543466.pathlight.network.ApiProvider;

public class AuthRepo {

    private final ApiProvider provider = new ApiProvider();

    public void login(String username, String password, String role, ResponseCallback<LoginResponse> callback) {
        LoginApi services = provider.getLoginApi();
        LoginRequest request = new LoginRequest(username, password, role);
        provider.format(services.login(request), LoginResponse.class, callback);
    }

    public void signupEmployer(EmployerSignupRequest request, ResponseCallback<BlankResponse> callback) {
        LoginApi services = provider.getLoginApi();
        provider.format(services.signupEmployer(request), BlankResponse.class, callback);
    }

    public void signupWorker(WorkerSignupRequest request, ResponseCallback<BlankResponse> callback) {
        LoginApi services = provider.getLoginApi();
        provider.format(services.signupWorker(request), BlankResponse.class, callback);
    }

    public void forgotPassword(String email, ResponseCallback<BlankResponse> callback) {
        LoginApi services = provider.getLoginApi();
        provider.format(services.forgotPwd(email), BlankResponse.class, callback);
    }
}
