package uk.ac.tees.w9543466.pathlight.employer.applications;

import java.util.List;

import uk.ac.tees.w9543466.pathlight.BaseResponse;

public class ApplicationsResponse extends BaseResponse {
    private List<WorkApplication> applications;

    public ApplicationsResponse() {
    }

    public ApplicationsResponse(List<WorkApplication> applications) {
        this.applications = applications;
    }

    public List<WorkApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<WorkApplication> workApplications) {
        this.applications = workApplications;
    }
}
