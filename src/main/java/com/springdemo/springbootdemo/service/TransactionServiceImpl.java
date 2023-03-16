package com.springdemo.springbootdemo.service;

import com.springdemo.springbootdemo.dao.TransactionDetailsRepository;
import com.springdemo.springbootdemo.entities.TransactionDetails;
import com.springdemo.springbootdemo.entities.TransactionType;
import com.springdemo.springbootdemo.user.CrmTransactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    @Override
    public List<TransactionDetails> findTransactionsByAccountNumber(int accountNumber) {
        return transactionDetailsRepository.findTransactionsByAccountNumber(accountNumber);
    }

}
