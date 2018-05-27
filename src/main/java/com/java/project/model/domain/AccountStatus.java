package com.java.project.model.domain;

/**
 * Represents status of the account.
 */
public enum AccountStatus {
    OPENED("opened"), CLOSED("closed"), UNDER_CONSIDERATION("under consideration");

    private String value;

    AccountStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
