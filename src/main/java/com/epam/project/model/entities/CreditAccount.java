package com.epam.project.model.entities;

import java.math.BigDecimal;

public class CreditAccount extends Account {

    private BigDecimal limit;
    private BigDecimal indebtedness;
    private BigDecimal accruedInterest;
    private BigDecimal creditRate;

    public CreditAccount() {

    }

    public CreditAccount(int userId,  BigDecimal limit, BigDecimal creditRate) {
        super(userId, limit);
        this.limit = limit;
        this.creditRate = creditRate;
    }

    public CreditAccount(int id, int userId,  BigDecimal limit, BigDecimal creditRate) {
        super(userId, limit);
        this.limit = limit;
        this.creditRate = creditRate;
        setId(id);
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public BigDecimal getIndebtedness() {
        return indebtedness;
    }

    public BigDecimal getAccruedInterest() {
        return accruedInterest;
    }

    public BigDecimal getCreditRate() {
        return creditRate;
    }
}
