package uk.ac.tees.w9543466.pathlight.auth;

public enum UserRole {
    WORKER("WORKER"), EMPLOYER("EMPLOYER");
    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
