package uk.ac.tees.w9543466.pathlight.employer.works;

import java.util.ArrayList;
import java.util.Objects;

import uk.ac.tees.w9543466.pathlight.KeyValueModel;
import uk.ac.tees.w9543466.pathlight.WorkStatus;

public class WorkItem {

    private long workId;
    private String title;
    private String skills;
    private double lat;
    private double lng;
    private long startTime;
    private double totalRate;
    private String status;
    private String createdBy;
    private String employerName;
    private boolean applied;

    public boolean isApplied() {
        return applied;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public long getWorkId() {
        return workId;
    }

    public void setWorkId(long workId) {
        this.workId = workId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public long getStartTime() {
        return startTime;
    }

    public String getFormattedStartTime() {
        //TODO
        return startTime + "";
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public double getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(double totalRate) {
        this.totalRate = totalRate;
    }

    public String getStatus() {
        return status;
    }

    public WorkStatus getWorkStatus() {
        return WorkStatus.valueOf(status.toUpperCase());
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<KeyValueModel> displayableList() {
        ArrayList<KeyValueModel> list = new ArrayList<>();
        list.add(new KeyValueModel("Skills required", skills));
        list.add(new KeyValueModel("Starts on", startTime + ""));//TODO format date
        list.add(new KeyValueModel("Pays", totalRate + " GBP"));
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkItem workItem = (WorkItem) o;
        return workId == workItem.workId && startTime == workItem.startTime && Objects.equals(title, workItem.title) && Objects.equals(createdBy, workItem.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workId, title, startTime, createdBy);
    }
}
