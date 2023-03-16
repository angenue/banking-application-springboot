package com.springdemo.springbootdemo.service;

import com.springdemo.springbootdemo.dao.AccountRepository;
import com.springdemo.springbootdemo.dao.PersonRepository;
import com.springdemo.springbootdemo.dao.TransactionDetailsRepository;
import com.springdemo.springbootdemo.entities.Account;
import com.springdemo.springbootdemo.entities.Person;
import com.springdemo.springbootdemo.entities.TransactionDetails;
import com.springdemo.springbootdemo.entities.TransactionType;
import com.springdemo.springbootdemo.user.CrmAccount;
import com.springdemo.springbootdemo.user.CrmPerson;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registerAccount(CrmPerson crmPerson, CrmAccount crmAccount) {
        Person person = new Person();

        person.setUsername(crmPerson.getUsername());
        person.setPassword(passwordEncoder.encode(crmPerson.getPassword()));
        person.setFirstName(crmPerson.getFirstName());
        person.setLastName(crmPerson.getLastName());
        person.setEmailAddress(crmPerson.getEmailAddress());

        Account account = new Account();

        account.addTransaction(new TransactionDetails(crmAccount.getBalance(), TransactionType.DEPOSIT));
        account.setAccountType(crmAccount.getAccountType());
        account.setBalance(crmAccount.getBalance());

        person.addAccount(account);

        personRepository.save(person);
    }


    @Override
    public void createAccount(String username, CrmAccount crmAccount) {
        Person person = findByUsernameIgnoreCase(username);
        person.addAccount(new Account(crmAccount.getAccountType(), crmAccount.getBalance()));

        personRepository.save(person);
    }

    @Override
    @Transactional
    public Person findByUsernameIgnoreCase(String username) {
        return personRepository.findByUsernameIgnoreCase(username);
    }

    @Override
    @Transactional
    public Person findByEmailAddressIgnoreCase(String emailAddress) {
        return personRepository.findByEmailAddressIgnoreCase(emailAddress);
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        personRepository.deletePersonByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)  {
        Person person = personRepository.findByUsernameIgnoreCase(username);

        if(person == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(person.getUsername(), person.getPassword(), getAuthorities());

    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>();
    }
}
