package uk.ac.tees.w9543466.pathlight.auth;


import retrofit2.Call;
import uk.ac.tees.w9543466.pathlight.ResponseCallback;
import uk.ac.tees.w9543466.pathlight.network.ApiProvider;

public class AuthRepo {

    private final ApiProvider provider = new ApiProvider();

    public void login(String username, String password, String role, ResponseCallback<LoginResponse> callback) {
        LoginApi services = provider.getLoginApi();
        Call<LoginResponse> call = services.login(new LoginRequest(username, password, role));
        provider.get(call, LoginResponse.class, callback::onResponse);
    }
}
