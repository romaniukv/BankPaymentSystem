package com.java.project.model.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

    private int id;
    private long senderAccountNumber;
    private long receiverAccountNumber;
    private BigDecimal amount;
    private Date date;

    public Transaction() {
    }

    public Transaction(long receiverAccountNumber, BigDecimal amount, Date date) {
        this.receiverAccountNumber = receiverAccountNumber;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(long senderAccountNumber, long receiverAccountNumber, BigDecimal amount, Date date) {
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(int id, long senderAccountNumber, long receiverAccountNumber, BigDecimal amount, Date date) {
        this.id = id;
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public long getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public long getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
