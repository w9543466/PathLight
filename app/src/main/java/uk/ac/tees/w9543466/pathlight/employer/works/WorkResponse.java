package uk.ac.tees.w9543466.pathlight.employer.works;

import java.util.List;

import uk.ac.tees.w9543466.pathlight.BaseResponse;

public class WorkResponse extends BaseResponse {

    private List<WorkItem> works;

    public List<WorkItem> getWorks() {
        return works;
    }

    public void setWorks(List<WorkItem> works) {
        this.works = works;
    }
}
