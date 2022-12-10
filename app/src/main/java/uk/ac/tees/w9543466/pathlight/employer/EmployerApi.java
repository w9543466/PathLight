package uk.ac.tees.w9543466.pathlight.employer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import uk.ac.tees.w9543466.pathlight.BlankResponse;
import uk.ac.tees.w9543466.pathlight.WorkResponse;
import uk.ac.tees.w9543466.pathlight.employer.applications.ApplicationsResponse;
import uk.ac.tees.w9543466.pathlight.employer.profile.EmployerProfileResponse;
import uk.ac.tees.w9543466.pathlight.employer.works.WorkRequest;
import uk.ac.tees.w9543466.pathlight.network.RawResponse;

public interface EmployerApi {
    @GET("/employer/profile")
    Call<RawResponse<EmployerProfileResponse>> getProfile();

    @GET("/employer/works")
    Call<RawResponse<WorkResponse>> getWorks();

    @POST("/employer/work")
    Call<RawResponse<BlankResponse>> createWork(@Body WorkRequest request);

    @PUT("/employer/work")
    Call<RawResponse<BlankResponse>> updateWork(@Body WorkRequest request);

    @GET("/employer/work/{workId}/application")
    Call<RawResponse<ApplicationsResponse>> getApplications(@Path("workId") long workId);

    @POST("/employer/work/application/{applicationId}/accept")
    Call<RawResponse<BlankResponse>> acceptApplication(@Path("applicationId") long applicationId);

    @POST("/employer/work/application/{applicationId}/reject")
    Call<RawResponse<BlankResponse>> rejectApplication(@Path("applicationId") long applicationId);

    @DELETE("/employer/work/{workId}")
    Call<RawResponse<WorkResponse>> deleteWork(@Path("workId") long applicationId);
}
