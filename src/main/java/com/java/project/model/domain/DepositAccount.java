package com.java.project.model.domain;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DepositAccount extends Account {

    private String name;
    private int term;
    private BigDecimal amount;
    private BigDecimal rate;

    {
        setStatus(AccountStatus.OPENED);
    }

    public DepositAccount() {
    }


    public DepositAccount(int id, String name, int term, BigDecimal rate) {
        this.name = name;
        this.term = term;
        this.rate = rate;
        setId(id);
    }

    public DepositAccount(int id, long number, BigDecimal balance, int userId, BigDecimal amount,
                          BigDecimal rate, int term, AccountStatus status, Date expirationDate) {
        super(id, number, userId, balance, status, expirationDate);
        this.amount = amount;
        this.rate = rate;
        this.term = term;
    }

    @Override
    public void calculateExpirationDate() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(Calendar.MONTH, 18);
        setExpirationDate(gregorianCalendar.getTime());
        setExpirationDate(gregorianCalendar.getTime());
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
