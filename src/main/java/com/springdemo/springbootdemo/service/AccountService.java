package com.springdemo.springbootdemo.service;

import com.springdemo.springbootdemo.entities.Account;
import com.springdemo.springbootdemo.entities.TransactionDetails;
import com.springdemo.springbootdemo.user.CrmAccount;

import java.util.List;

public interface AccountService {
    List<Account> findAccountsByUsername(String username);

    Account findById(int accountNumber);

    void addAccount(String name, CrmAccount crmAccount);

    void deposit(int accountNumber, CrmAccount crmAccount);

    void withdraw(int accountNumber, CrmAccount crmAccount);

    void deleteAccountByAccountNumber(int accountNumber);
}
