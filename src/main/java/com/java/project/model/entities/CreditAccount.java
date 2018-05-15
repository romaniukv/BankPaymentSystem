package com.java.project.model.entities;

import java.math.BigDecimal;
import java.util.Date;

public class CreditAccount extends Account {

    private BigDecimal limit;
    private BigDecimal indebtedness;
    private BigDecimal accruedInterest;
    private BigDecimal creditRate;

    public CreditAccount() {

    }

    public CreditAccount(long number, int userId,  BigDecimal limit, BigDecimal creditRate) {
        super(number, userId, limit);
        this.limit = limit;
        this.creditRate = creditRate;
        setBalance(limit);
    }

    public CreditAccount(long number, int id, int userId,  BigDecimal limit, BigDecimal creditRate) {
        super(number, userId, limit);
        this.limit = limit;
        this.creditRate = creditRate;
        setId(id);
        setBalance(limit);
    }

    public CreditAccount(int id, long number, BigDecimal balance, int userId, BigDecimal limit,
                         BigDecimal indebtedness, BigDecimal accruedInterest, BigDecimal creditRate,
                         AccountStatus status, Date expirationDate) {
        super(id, number, userId, balance, status, expirationDate);
        this.limit = limit;
        this.indebtedness = indebtedness;
        this.accruedInterest = accruedInterest;
        this.creditRate = creditRate;
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
