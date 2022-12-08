package uk.ac.tees.w9543466.pathlight.worker.apimodel;

public class ApplyWorkRequest {
    private double proposedRate;
    private long workId;

    public double getProposedRate() {
        return proposedRate;
    }

    public void setProposedRate(double proposedRate) {
        this.proposedRate = proposedRate;
    }

    public long getWorkId() {
        return workId;
    }

    public void setWorkId(long workId) {
        this.workId = workId;
    }
}
