package com.springdemo.springbootdemo.service;

import com.springdemo.springbootdemo.dao.AccountRepository;
import com.springdemo.springbootdemo.dao.PersonRepository;
import com.springdemo.springbootdemo.entities.Account;
import com.springdemo.springbootdemo.entities.Person;
import com.springdemo.springbootdemo.entities.TransactionDetails;
import com.springdemo.springbootdemo.entities.TransactionType;
import com.springdemo.springbootdemo.user.CrmAccount;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PersonRepository personRepository;
    @Override
    public List<Account> findAccountsByUsername(String username) {
        return accountRepository.findAccountsByUsername(username);
    }

    @Override
    public Account findById(int accountNumber) {
        Optional<Account> result = accountRepository.findById(accountNumber);

        Account account = null;

        if(result.isPresent()) {
            account = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find account - " + accountNumber);
        }
        return account;
    }

    @Override
    public void addAccount(String name, CrmAccount crmAccount) {
        Account account = new Account();
        account.setUsername(name);

        account.setAccountType(crmAccount.getAccountType());
        account.setBalance(crmAccount.getBalance());
        account.addTransaction(new TransactionDetails(crmAccount.getBalance(), TransactionType.DEPOSIT));



        accountRepository.save(account);
    }

    @Override
    public void deposit(int accountNumber, CrmAccount crmAccount) {
        Account account = findById(accountNumber);

        account.setBalance(account.getBalance() + crmAccount.getBalance());
        account.addTransaction(new TransactionDetails(crmAccount.getBalance(), TransactionType.DEPOSIT));

        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void deleteAccountByAccountNumber(int accountNumber) {
        accountRepository.deleteAccountByAccountNumber(accountNumber);
    }

    @Override
    public void withdraw(int accountNumber, CrmAccount crmAccount) {
        Account account = findById(accountNumber);

        account.setBalance(account.getBalance() - crmAccount.getBalance());
        account.addTransaction(new TransactionDetails(crmAccount.getBalance(), TransactionType.WITHDRAW));

        accountRepository.save(account);

    }
}
