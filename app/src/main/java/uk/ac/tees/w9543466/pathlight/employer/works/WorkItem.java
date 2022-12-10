package uk.ac.tees.w9543466.pathlight.employer.works;

import static uk.ac.tees.w9543466.pathlight.utils.TimeFormatterUtil.format;

import java.util.ArrayList;
import java.util.Objects;

import uk.ac.tees.w9543466.pathlight.KeyValueModel;
import uk.ac.tees.w9543466.pathlight.WorkStatus;

public class WorkItem {

    private long id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return format(startTime);
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
        list.add(new KeyValueModel("Starts on", format(startTime) + ""));
        list.add(new KeyValueModel("Pays", totalRate + " GBP"));
        return list;
    }

    @Override
    public String toString() {
        return "WorkItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", skills='" + skills + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", startTime=" + startTime +
                ", totalRate=" + totalRate +
                ", status='" + status + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", employerName='" + employerName + '\'' +
                ", applied=" + applied +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkItem workItem = (WorkItem) o;
        return id == workItem.id && startTime == workItem.startTime && Objects.equals(title, workItem.title) && Objects.equals(createdBy, workItem.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, startTime, createdBy);
    }
}
