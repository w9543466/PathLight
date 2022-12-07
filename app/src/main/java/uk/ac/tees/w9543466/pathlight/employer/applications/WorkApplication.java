package uk.ac.tees.w9543466.pathlight.employer.applications;

import java.util.Objects;

import uk.ac.tees.w9543466.pathlight.worker.WorkerDto;

public class WorkApplication {
    private Long id;
    private Long workerId;
    private Long workId;
    private Double rate;
    private ApplicationStatus applicationStatus;
    private long createdDate;
    private WorkerDto worker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public WorkerDto getWorker() {
        return worker;
    }

    public void setWorker(WorkerDto worker) {
        this.worker = worker;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkApplication that = (WorkApplication) o;
        return createdDate == that.createdDate && Objects.equals(id, that.id) && Objects.equals(workerId, that.workerId) && Objects.equals(workId, that.workId) && Objects.equals(rate, that.rate) && applicationStatus == that.applicationStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workerId, workId, rate, applicationStatus, createdDate);
    }
}
