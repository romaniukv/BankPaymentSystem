package com.java.project.model.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

    private int id;
    private long fromNumber;
    private long toNumber;
    private BigDecimal amount;
    private Date date;

    public Transaction() {
    }

    public Transaction(long fromNumber, long toNumber, BigDecimal amount, Date date) {
        this.fromNumber = fromNumber;
        this.toNumber = toNumber;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(int id, long fromNumber, long toNumber, BigDecimal amount, Date date) {
        this.id = id;
        this.fromNumber = fromNumber;
        this.toNumber = toNumber;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public long getFromNumber() {
        return fromNumber;
    }

    public long getToNumber() {
        return toNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
