package uk.ac.tees.w9543466.pathlight.auth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import uk.ac.tees.w9543466.pathlight.BlankResponse;
import uk.ac.tees.w9543466.pathlight.auth.models.EmployerSignupRequest;
import uk.ac.tees.w9543466.pathlight.auth.models.LoginRequest;
import uk.ac.tees.w9543466.pathlight.auth.models.LoginResponse;
import uk.ac.tees.w9543466.pathlight.auth.models.WorkerSignupRequest;
import uk.ac.tees.w9543466.pathlight.network.RawResponse;

public interface LoginApi {
    @POST("/login")
    Call<RawResponse<LoginResponse>> login(@Body LoginRequest request);

    @POST("/signup/employer")
    Call<RawResponse<BlankResponse>> signupEmployer(@Body EmployerSignupRequest request);

    @POST("/signup/worker")
    Call<RawResponse<BlankResponse>> signupWorker(@Body WorkerSignupRequest request);
}
