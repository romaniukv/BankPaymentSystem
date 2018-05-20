package com.java.project.model.domain;

import java.math.BigDecimal;
import java.util.Date;

public class DepositReplenishment extends Transaction {

    public DepositReplenishment(long senderAccountNumber, long receiverAccountNumber, BigDecimal amount, Date date) {
        super(senderAccountNumber, receiverAccountNumber, amount, date);
    }

    public DepositReplenishment(int id, long senderAccountNumber, long receiverAccountNumber, BigDecimal amount, Date date) {
        super(id, senderAccountNumber, receiverAccountNumber, amount, date);
    }
}
