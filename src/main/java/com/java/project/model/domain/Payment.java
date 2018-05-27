package com.java.project.model.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents credit account payment.
 */
public class Payment extends Transaction {

    /**
     * Full name of sender.
     */
    private String senderFullName;

    /**
     * Full name of receiver.
     */
    private String receiverFullName;

    /**
     * Purpose of payment.
     */
    private String purpose;

    public Payment() {

    }

    /**
     * Constructor which is used for reading existing replenishment from database and for creating
     * new replenishment.
     */
    public Payment(String senderFullName, long fromNumber, String receiverFullName, long toNumber, BigDecimal amount,
                   String purpose, Date date) {
        super(fromNumber, toNumber, amount, date);
        this.senderFullName = senderFullName;
        this.receiverFullName = receiverFullName;
        this.purpose = purpose;
    }

    public String getSenderFullName() {
        return senderFullName;
    }

    public void setSenderFullName(String senderFullName) {
        this.senderFullName = senderFullName;
    }

    public String getReceiverFullName() {
        return receiverFullName;
    }

    public void setReceiverFullName(String receiverFullName) {
        this.receiverFullName = receiverFullName;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
