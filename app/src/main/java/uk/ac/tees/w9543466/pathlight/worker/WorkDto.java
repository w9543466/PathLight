package uk.ac.tees.w9543466.pathlight.worker;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import uk.ac.tees.w9543466.pathlight.utils.TimeFormatterUtil;

public class WorkDto {
    private long id;
    private final ObservableBoolean applyEnabled = new ObservableBoolean(true);
    private final ObservableBoolean applyInProgress = new ObservableBoolean(false);
    private final ObservableBoolean alreadyApplied = new ObservableBoolean(false);
    private final ObservableField<String> title = new ObservableField<>();
    private final ObservableField<String> startTime = new ObservableField<>();
    private final ObservableField<String> totalRate = new ObservableField<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ObservableBoolean isAlreadyApplied() {
        return alreadyApplied;
    }

    public void setAlreadyApplied(boolean alreadyApplied) {
        this.alreadyApplied.set(alreadyApplied);
    }

    public ObservableBoolean isApplyEnabled() {
        return applyEnabled;
    }

    public void setApplyEnabled(boolean applyEnabled) {
        this.applyEnabled.set(applyEnabled);
    }

    public ObservableBoolean getApplyInProgress() {
        return applyInProgress;
    }

    public void setApplyInProgress(boolean applyInProgress) {
        this.applyInProgress.set(applyInProgress);
    }

    public ObservableField<String> getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public ObservableField<String> getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime.set(startTime + "");
    }

    public ObservableField<String> getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(double totalRate) {
        this.totalRate.set(totalRate + "");
    }

    public String getFormattedStartTime() {
        String millisAsString = startTime.get();
        try {
            long millis = Long.parseLong(millisAsString);
            return TimeFormatterUtil.format(millis);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return startTime.get() + "";
    }
}
