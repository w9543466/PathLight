package uk.ac.tees.w9543466.pathlight.employer;

import retrofit2.Call;
import retrofit2.http.GET;
import uk.ac.tees.w9543466.pathlight.network.RawResponse;

public interface EmployerApi {
    @GET("/employer/profile")
    Call<RawResponse<ProfileResponse>> getProfile();

    @GET("/employer/works")
    Call<RawResponse<WorkResponse>> getWorks();
}
