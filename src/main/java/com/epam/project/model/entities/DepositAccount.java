package com.epam.project.model.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DepositAccount extends Account {

    private double amountOfDeposit;
    private double rate;
    private List<Operation> historyOfReplenishment;

    public DepositAccount(int userId, double rate) {
        super(userId, new BigDecimal("0"));
        historyOfReplenishment = new ArrayList<>();
        this.rate = rate;
    }

    public double getAmountOfDeposit() {
        return amountOfDeposit;
    }

    public double getRate() {
        return rate;
    }

    public List<Operation> getHistoryOfReplenishment() {
        return historyOfReplenishment;
    }
}
