package com.epam.project.config;

public enum Role {
    ADMINISTRATOR_ROLE("ADMINISTRATOR"), USER_ROLE("USER");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
