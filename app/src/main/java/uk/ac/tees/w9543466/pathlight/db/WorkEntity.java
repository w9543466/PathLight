package uk.ac.tees.w9543466.pathlight.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_work")
public class WorkEntity {
    @PrimaryKey
    @ColumnInfo(name = "work_id")
    private long workId;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "skills")
    private String skills;
    @ColumnInfo(name = "lat")
    private double lat;
    @ColumnInfo(name = "lng")
    private double lng;
    @ColumnInfo(name = "expiry")
    private double expiryDate;
    @ColumnInfo(name = "start")
    private double startTime;
    @ColumnInfo(name = "duration")
    private double workDuration;
    @ColumnInfo(name = "duration_unit")
    private double workDurationUnit;
    @ColumnInfo(name = "rate")
    private double totalRate;
    @ColumnInfo(name = "status")
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

    public void setStatus(String status) {
        this.status = status;
    }
}
