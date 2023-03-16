package com.springdemo.springbootdemo.dao;

import com.springdemo.springbootdemo.entities.Account;
import com.springdemo.springbootdemo.entities.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Integer> {

    @Query("select t from Account a inner join TransactionDetails t on t.account.accountNumber = a.accountNumber where a.accountNumber = ?1")
    List<TransactionDetails> findTransactionsByAccountNumber(int accountNumber);
}
