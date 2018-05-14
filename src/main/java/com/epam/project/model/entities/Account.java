package com.epam.project.model.entities;

import java.math.BigDecimal;
import java.util.*;

public abstract class Account {

    private int id;
    private long number;
    private BigDecimal balance;
    private int userId;
    private Date expirationDate;
    private AccountStatus status;

    //for AbstractDAO
    public Account() {
        expirationDate = calculateExpirationDate();
    }

    //for creating new
    public Account(long number, int userId, BigDecimal balance) {
        this.number = number;
        this.userId = userId;
        this.balance = balance;
        this.status = AccountStatus.UNDER_CONSIDERATION;
        this.expirationDate = calculateExpirationDate();
    }

    //for reading from database
    public Account(int id, long number, int userId, BigDecimal balance, AccountStatus status, Date expirationDate) {
        this.id = id;
        this.number = number;
        this.userId = userId;
        this.balance = balance;
        this.status = status;
        this.expirationDate = expirationDate;
    }

    private Date calculateExpirationDate() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(Calendar.YEAR, 2);
        return gregorianCalendar.getTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}
