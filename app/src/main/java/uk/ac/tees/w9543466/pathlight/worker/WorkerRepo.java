package uk.ac.tees.w9543466.pathlight.worker;

import android.content.Context;

import uk.ac.tees.w9543466.pathlight.BlankResponse;
import uk.ac.tees.w9543466.pathlight.ResponseCallback;
import uk.ac.tees.w9543466.pathlight.WorkResponse;
import uk.ac.tees.w9543466.pathlight.employer.profile.EmployerProfileResponse;
import uk.ac.tees.w9543466.pathlight.network.ApiProvider;
import uk.ac.tees.w9543466.pathlight.worker.apimodel.ApplyWorkRequest;
import uk.ac.tees.w9543466.pathlight.worker.apimodel.WorkerApplicationResponse;

public class WorkerRepo {
    private final ApiProvider provider;
    private final WorkerApi services;

    public WorkerRepo(Context context) {
        provider = new ApiProvider(context);
        services = provider.getWorkerApi();
    }

    public void getProfile(ResponseCallback<EmployerProfileResponse> callback) {
        provider.format(services.getProfile(), EmployerProfileResponse.class, callback);
    }

    public void getApplications(ResponseCallback<WorkerApplicationResponse> callback) {
        provider.format(services.getApplications(), WorkerApplicationResponse.class, callback);
    }

    public void getWorks(ResponseCallback<WorkResponse> callback) {
        provider.format(services.getAllWorks(), WorkResponse.class, callback);
    }

    public void apply(ApplyWorkRequest request, ResponseCallback<BlankResponse> callback) {
        provider.format(services.apply(request), BlankResponse.class, callback);
    }
}
