package com.epam.project.model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Account {

    private double balance;
    private int userId;
    private List<Operation> history;
    private Date  expirationDate;

    public Account() {
        history = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
}
