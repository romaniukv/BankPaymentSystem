package com.epam.project.model.entities;

import java.util.Date;

public class CreditAccount extends Account {

    private double limit;
    private double indebtedness;
    private double accruedInterest;
    private double creditRate;

    public CreditAccount(double limit, double creditRate) {
        this.limit = limit;
        this.creditRate = creditRate;
    }

    public double getLimit() {
        return limit;
    }

    public double getIndebtedness() {
        return indebtedness;
    }

    public double getAccruedInterest() {
        return accruedInterest;
    }

    public double getCreditRate() {
        return creditRate;
    }
}
