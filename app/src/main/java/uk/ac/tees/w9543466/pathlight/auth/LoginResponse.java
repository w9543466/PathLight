package uk.ac.tees.w9543466.pathlight.auth;

import uk.ac.tees.w9543466.pathlight.BaseResponse;

public class LoginResponse extends BaseResponse {
    private UserRole role;

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
