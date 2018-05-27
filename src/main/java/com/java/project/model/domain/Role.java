package com.java.project.model.domain;

/**
 * Role of user in system(admin or user).
 */
public enum Role {
    ADMIN("ADMIN"), USER("USER");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
