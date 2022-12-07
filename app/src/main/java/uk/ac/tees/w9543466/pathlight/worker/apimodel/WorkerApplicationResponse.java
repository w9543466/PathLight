package uk.ac.tees.w9543466.pathlight.worker.apimodel;

import java.util.List;

import uk.ac.tees.w9543466.pathlight.BaseResponse;

public class WorkerApplicationResponse extends BaseResponse {
    public WorkerApplicationResponse(List<WorkerApplicationItem> works) {
        this.works = works;
    }

    private List<WorkerApplicationItem> works;

    public List<WorkerApplicationItem> getWorks() {
        return works;
    }

    public void setWorks(List<WorkerApplicationItem> works) {
        this.works = works;
    }
}
