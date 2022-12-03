package uk.ac.tees.w9543466.pathlight.employer;

import android.content.Context;

import uk.ac.tees.w9543466.pathlight.ResponseCallback;
import uk.ac.tees.w9543466.pathlight.network.ApiProvider;

public class EmployerRepo {

    private final ApiProvider provider;
    private final EmployerApi services;

    public EmployerRepo(Context context) {
        provider = new ApiProvider(context);
        services = provider.getEmployerApi();
    }

    public void getProfile(ResponseCallback<ProfileResponse> callback) {
        provider.format(services.getProfile(), ProfileResponse.class, callback);
    }

    public void getWorks(ResponseCallback<WorkResponse> callback) {
        provider.format(services.getWorks(), WorkResponse.class, callback);
    }
}
