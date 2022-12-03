package uk.ac.tees.w9543466.pathlight;

public enum WorkStatus {
    NOT_STARTED("not_started"),
    STARTED("started"),
    CANCELLED("cancelled"),
    NO_SHOW_W("no_show_worker"),
    NO_SHOW_E("no_show_employer"),
    NO_SHOW("no_show"),
    COMPLETED_W("completed_worker"),
    COMPLETED_E("completed_employer"),
    COMPLETED("completed");
    private final String status;

    WorkStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String displayableStatus() {
        switch (this) {
            case NOT_STARTED:
                return "Not started";
            case STARTED:
                return "Started";
            case CANCELLED:
                return "Cancelled";
            case NO_SHOW_W:
                return "Worker didn't show up";
            case NO_SHOW_E:
                return "Employer didn't show up";
            case NO_SHOW:
                return "No show";
            case COMPLETED_W:
                return "Completed by worker";
            case COMPLETED_E:
                return "Completed by employer";
            case COMPLETED:
                return "Completed";
            default:
                return "Unknown status";
        }
    }
}