package com.springdemo.springbootdemo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private int accountNumber;

    @Column(name = "username")
    private String username;

    @Enumerated(EnumType.STRING)
    @NotNull(message="is required")
    @Column(name="acc_type", columnDefinition = "enum")
    private Type accountType;

    @NotNull(message="is required")
    @Column(name = "acc_balance")
    private Integer balance = null;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="account_number")
    private List<TransactionDetails> transactionDetails;

    public Account() {
    }

    public Account(Type accountType, Integer balance) {
        this.accountType = accountType;
        this.balance = balance;
    }

    public void addTransaction(TransactionDetails transaction) {
        if(transactionDetails == null) {
            transactionDetails = new ArrayList<>();
        }

        transactionDetails.add(transaction);
    }

    public List<TransactionDetails> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(List<TransactionDetails> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public Type getAccountType() {
        return accountType;
    }

    public void setAccountType(Type accountType) {
        this.accountType = accountType;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return
                "Account Number: " + accountNumber + "\n" +
                "Account Type: " + accountType + "\n" +
                "Balance: " + balance + "\n";
    }
}
