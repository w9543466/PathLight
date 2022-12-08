package uk.ac.tees.w9543466.pathlight.worker.apimodel;

import java.util.List;

import uk.ac.tees.w9543466.pathlight.BaseResponse;

public class WorkerApplicationResponse extends BaseResponse {
    public WorkerApplicationResponse() {
    }

    public WorkerApplicationResponse(List<WorkerApplicationItem> applications) {
        this.applications = applications;
    }

    private List<WorkerApplicationItem> applications;

    public List<WorkerApplicationItem> getApplications() {
        return applications;
    }

    public void setApplications(List<WorkerApplicationItem> applications) {
        this.applications = applications;
    }
}
