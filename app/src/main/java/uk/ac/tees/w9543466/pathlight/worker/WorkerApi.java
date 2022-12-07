package uk.ac.tees.w9543466.pathlight.worker;

import retrofit2.Call;
import retrofit2.http.GET;
import uk.ac.tees.w9543466.pathlight.employer.profile.EmployerProfileResponse;
import uk.ac.tees.w9543466.pathlight.WorkResponse;
import uk.ac.tees.w9543466.pathlight.network.RawResponse;
import uk.ac.tees.w9543466.pathlight.worker.apimodel.WorkerApplicationResponse;

public interface WorkerApi {
    @GET("/worker/profile")
    Call<RawResponse<EmployerProfileResponse>> getProfile();

    @GET("/worker/works/application")
    Call<RawResponse<WorkerApplicationResponse>> getApplications();

    @GET("/worker/works")
    Call<RawResponse<WorkResponse>> getAllWorks();
}
