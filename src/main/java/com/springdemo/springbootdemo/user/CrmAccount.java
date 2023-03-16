package com.springdemo.springbootdemo.user;

import com.springdemo.springbootdemo.entities.Type;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class CrmAccount {
    @Enumerated(EnumType.STRING)
    @NotNull(message="is required")
    private Type accountType;

    @NotNull(message="is required")
    private Integer balance = null;

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
}
