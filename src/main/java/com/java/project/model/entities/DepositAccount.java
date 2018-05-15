package com.java.project.model.entities;

import java.math.BigDecimal;

public class DepositAccount extends Account {

    private String name;
    private int term;
    private BigDecimal amount;
    private BigDecimal rate;

    public DepositAccount() {
    }

    public DepositAccount(int id, String name, int term, BigDecimal rate) {
        this.name = name;
        this.term = term;
        this.rate = rate;
        setId(id);
    }

    public DepositAccount(long number, int userId, BigDecimal amount, BigDecimal rate) {
        super(number, userId, amount);
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
