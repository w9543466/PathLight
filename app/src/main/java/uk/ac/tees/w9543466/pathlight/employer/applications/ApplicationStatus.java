package uk.ac.tees.w9543466.pathlight.employer.applications;

public enum ApplicationStatus {
    APPLIED("applied"), REJECTED("rejected"), ACCEPTED("accepted");
    private final String status;

    ApplicationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String displayableStatus() {
        switch (this) {
            case APPLIED:
                return "Applied";
            case REJECTED:
                return "Rejected";
            case ACCEPTED:
                return "Accepted";
            default:
                return "Unknown status";
        }
    }
}
