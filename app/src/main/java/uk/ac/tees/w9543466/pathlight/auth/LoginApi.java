package uk.ac.tees.w9543466.pathlight.auth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {
    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
