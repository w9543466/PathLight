package uk.ac.tees.w9543466.pathlight.employer.applications;

import java.util.List;

import uk.ac.tees.w9543466.pathlight.BaseResponse;

public class ApplicationsResponse extends BaseResponse {
    private List<WorkApplication> workApplications;

    public ApplicationsResponse(List<WorkApplication> workApplications) {
        this.workApplications = workApplications;
    }

    public List<WorkApplication> getApplications() {
        return workApplications;
    }

    public void setApplications(List<WorkApplication> workApplications) {
        this.workApplications = workApplications;
    }
}
