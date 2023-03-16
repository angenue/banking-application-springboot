package com.springdemo.springbootdemo.dao;

import com.springdemo.springbootdemo.entities.Account;
import com.springdemo.springbootdemo.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    @Query("select u from Person u where u.username =?1")
    Person findByUsernameIgnoreCase(String username); //check if username is taken

    @Query( "select u from Person u where u.emailAddress =?1")
    Person findByEmailAddressIgnoreCase(String emailAddress); //check if email is taken

    void deletePersonByUsername(String username);
}
