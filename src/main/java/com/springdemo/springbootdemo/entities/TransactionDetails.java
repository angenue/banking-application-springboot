package com.springdemo.springbootdemo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

@Entity
@Table(name = "transaction_details")
public class TransactionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionID;

    @Column(name = "transaction_amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "account_number")
    private Account account;

    //@Column(name = "account_number")
    //private int accountNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "transaction_date")
    private Date transactionDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message="is required")
    @Column(name = "transaction_type", columnDefinition = "enum")
    private TransactionType transactionType;

    public TransactionDetails() {
    }

    public TransactionDetails(int amount, TransactionType transactionType) {
        this.amount = amount;
        this.transactionType = transactionType;
        //this.transactionDate = transactionDate;

        long millis=System.currentTimeMillis();
        // creating a new object of the class Date
        this.transactionDate = new Date(millis);
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }


    public int getTransactionID() {
        return transactionID;
    }

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

    @Override
    public String toString() {
        return "TransactionDetails{" +
                "amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
