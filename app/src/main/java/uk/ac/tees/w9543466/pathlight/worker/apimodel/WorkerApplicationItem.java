package uk.ac.tees.w9543466.pathlight.worker.apimodel;

import java.time.Instant;

import uk.ac.tees.w9543466.pathlight.employer.applications.ApplicationStatus;
import uk.ac.tees.w9543466.pathlight.employer.profile.EmployerProfileResponse;

public class WorkerApplicationItem {
    private Long id;
    private Long workerId;
    private Long workId;
    private Double rate;
    private ApplicationStatus applicationStatus;
    private Instant createdDate;
    private EmployerProfileResponse employer;
    private WorkerProfileResponse work;

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

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public EmployerProfileResponse getEmployer() {
        return employer;
    }

    public void setEmployer(EmployerProfileResponse employer) {
        this.employer = employer;
    }

    public WorkerProfileResponse getWork() {
        return work;
    }

    public void setWork(WorkerProfileResponse work) {
        this.work = work;
    }
}
