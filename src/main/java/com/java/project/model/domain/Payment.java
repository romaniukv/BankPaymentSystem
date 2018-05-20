package com.java.project.model.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Payment extends Transaction {

    private String senderFullName;
    private String receiverFullName;
    private String purpose;

    public Payment() {

    }

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
