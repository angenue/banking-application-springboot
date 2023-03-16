package com.springdemo.springbootdemo.dao;

import com.springdemo.springbootdemo.entities.Account;
import com.springdemo.springbootdemo.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("select a from Person p inner join Account a on a.username = p.username where p.username = ?1")
    List<Account> findAccountsByUsername(String username);

    void deleteAccountByAccountNumber(int accountNumber);
}
