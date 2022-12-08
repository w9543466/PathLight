package uk.ac.tees.w9543466.pathlight;

import java.util.List;

import uk.ac.tees.w9543466.pathlight.BaseResponse;
import uk.ac.tees.w9543466.pathlight.employer.works.WorkItem;

public class WorkResponse extends BaseResponse {
    public WorkResponse() {
    }

    private List<WorkItem> works;

    public List<WorkItem> getWorks() {
        return works;
    }

    public void setWorks(List<WorkItem> works) {
        this.works = works;
    }
}
