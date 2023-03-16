package com.springdemo.springbootdemo.service;

import com.springdemo.springbootdemo.entities.TransactionDetails;
import com.springdemo.springbootdemo.entities.TransactionType;
import com.springdemo.springbootdemo.user.CrmTransactions;

import java.util.List;

public interface TransactionService {
    List<TransactionDetails> findTransactionsByAccountNumber(int accountNumber);


}
