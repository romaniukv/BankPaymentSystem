package com.java.project.model.domain;

import com.java.project.utils.LocalizationUtils;

/**
 * Represents status of the account.
 */
public enum AccountStatus {
    OPENED(LocalizationUtils.OPENED), CLOSED(LocalizationUtils.CLOSED),
    UNDER_CONSIDERATION(LocalizationUtils.UNDER_CONSIDERATION);

    private String value;

    AccountStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
