package com.epam.project.model.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DepositAccount extends Account {

    private double amountOfDeposit;
    private double rate;

    public DepositAccount() {
    }

    public DepositAccount(long number, int userId, double rate) {
        super(number, userId, new BigDecimal("0"));

        this.rate = rate;
    }

    public double getAmountOfDeposit() {
        return amountOfDeposit;
    }

    public double getRate() {
        return rate;
    }

}
