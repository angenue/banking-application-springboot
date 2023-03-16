package com.springdemo.springbootdemo.user;

import com.springdemo.springbootdemo.entities.Account;
import com.springdemo.springbootdemo.entities.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class CrmTransactions {
    private int amount;

    private Date transactionDate;

    private TransactionType transactionType;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
