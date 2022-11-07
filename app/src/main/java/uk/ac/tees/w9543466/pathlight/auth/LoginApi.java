package uk.ac.tees.w9543466.pathlight.auth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import uk.ac.tees.w9543466.pathlight.network.RawResponse;

public interface LoginApi {
    @POST("/login")
    Call<RawResponse<LoginResponse>> login(@Body LoginRequest request);
}
