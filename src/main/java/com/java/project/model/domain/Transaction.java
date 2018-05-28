package com.java.project.model.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents credit account transaction.
 */
public class Transaction {

    /**
     * Id of transaction
     */
    private int id;

    /**
     * Sender's account number.
     */
    private long senderAccountNumber;

    /**
     * Receiver's account number.
     */
    private long receiverAccountNumber;

    /**
     * Amount of transaction.
     */
    private BigDecimal amount;

    /**
     * Date of transaction.
     */
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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
