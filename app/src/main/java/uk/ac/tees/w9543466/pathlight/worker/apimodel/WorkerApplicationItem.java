package uk.ac.tees.w9543466.pathlight.worker.apimodel;

import java.time.Instant;
import java.util.Objects;

import uk.ac.tees.w9543466.pathlight.employer.applications.ApplicationStatus;
import uk.ac.tees.w9543466.pathlight.employer.profile.EmployerProfileResponse;
import uk.ac.tees.w9543466.pathlight.employer.works.WorkItem;

public class WorkerApplicationItem {
    private Long id;
    private Long workerId;
    private Long workId;
    private Double rate;
    private ApplicationStatus applicationStatus;
    private long createdDate;
    private EmployerProfileResponse employer;
    private WorkItem work;

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

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public EmployerProfileResponse getEmployer() {
        return employer;
    }

    public void setEmployer(EmployerProfileResponse employer) {
        this.employer = employer;
    }

    public WorkItem getWork() {
        return work;
    }

    public void setWork(WorkItem work) {
        this.work = work;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkerApplicationItem that = (WorkerApplicationItem) o;
        return Objects.equals(id, that.id) && Objects.equals(workerId, that.workerId) && Objects.equals(workId, that.workId) && applicationStatus == that.applicationStatus && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workerId, workId, applicationStatus, createdDate);
    }
}
