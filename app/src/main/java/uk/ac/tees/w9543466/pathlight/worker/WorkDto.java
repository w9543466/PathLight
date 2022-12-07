package uk.ac.tees.w9543466.pathlight.worker;

import java.util.Objects;

public class WorkDto {
    private long id;
    private boolean applyEnabled;
    private boolean applyInProgress;
    private boolean alreadyApplied;
    private String title;
    private double startTime;
    private double totalRate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAlreadyApplied() {
        return alreadyApplied;
    }

    public void setAlreadyApplied(boolean alreadyApplied) {
        this.alreadyApplied = alreadyApplied;
    }

    public boolean isApplyEnabled() {
        return applyEnabled;
    }

    public void setApplyEnabled(boolean applyEnabled) {
        this.applyEnabled = applyEnabled;
    }

    public boolean isApplyInProgress() {
        return applyInProgress;
    }

    public void setApplyInProgress(boolean applyInProgress) {
        this.applyInProgress = applyInProgress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(double totalRate) {
        this.totalRate = totalRate;
    }

    public String getFormattedStartTime() {
        //TODO
        return startTime + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkDto workDto = (WorkDto) o;
        return id == workDto.id && Double.compare(workDto.startTime, startTime) == 0 && Double.compare(workDto.totalRate, totalRate) == 0 && Objects.equals(title, workDto.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, startTime, totalRate);
    }
}
