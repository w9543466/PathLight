package uk.ac.tees.w9543466.pathlight.employer.works;

import java.util.ArrayList;
import java.util.Objects;

import uk.ac.tees.w9543466.pathlight.WorkStatus;
import uk.ac.tees.w9543466.pathlight.KeyValueModel;

public class WorkItem {

    private long workId;
    private String title;
    private String skills;
    private double lat;
    private double lng;
    private double expiryDate;
    private double startTime;
    private double workDuration;
    private double workDurationUnit;
    private double totalRate;
    private String status;

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

    public double getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(double expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getStartTime() {
        return startTime;
    }

    public String getFormattedStartTime() {
        //TODO
        return startTime + "";
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration(double workDuration) {
        this.workDuration = workDuration;
    }

    public double getWorkDurationUnit() {
        return workDurationUnit;
    }

    public void setWorkDurationUnit(double workDurationUnit) {
        this.workDurationUnit = workDurationUnit;
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
        list.add(new KeyValueModel("Expires on", expiryDate + ""));//TODO format date
        list.add(new KeyValueModel("Starts on", startTime + ""));//TODO format date
        list.add(new KeyValueModel("Duration", workDuration + " " + workDurationUnit));//TODO format
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkItem workItem = (WorkItem) o;
        return workId == workItem.workId && Double.compare(workItem.lat, lat) == 0 && Double.compare(workItem.lng, lng) == 0 && Double.compare(workItem.expiryDate, expiryDate) == 0 && Double.compare(workItem.startTime, startTime) == 0 && Double.compare(workItem.workDuration, workDuration) == 0 && Double.compare(workItem.workDurationUnit, workDurationUnit) == 0 && Double.compare(workItem.totalRate, totalRate) == 0 && Objects.equals(title, workItem.title) && Objects.equals(skills, workItem.skills) && Objects.equals(status, workItem.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workId, title, skills, lat, lng, expiryDate, startTime, workDuration, workDurationUnit, totalRate, status);
    }
}
