package com.authentication.authentication.domain.users;

public enum UsersRoles {
    ADMIN("admin"), USER("user");

    private final String role;

    UsersRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
