package com.epam.project.model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Account {

    private double balance;
    private List<Operation> history;
    private Date  expirationDate;

    public Account(Date expirationDate) {
        this.expirationDate = expirationDate;
        history = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
}
