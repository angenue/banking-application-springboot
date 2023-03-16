package com.springdemo.springbootdemo.service;

import com.springdemo.springbootdemo.entities.Account;
import com.springdemo.springbootdemo.entities.Person;
import com.springdemo.springbootdemo.entities.TransactionDetails;

import com.springdemo.springbootdemo.user.CrmAccount;
import com.springdemo.springbootdemo.user.CrmPerson;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface PersonService extends UserDetailsService {
    Person findByUsernameIgnoreCase(String username);
    Person findByEmailAddressIgnoreCase(String emailAddress);

    void registerAccount(CrmPerson crmPerson, CrmAccount crmAccount);

    void createAccount(String username, CrmAccount crmAccount);

    void deleteByUsername(String username);


}
