package com.springdemo.springbootdemo.user;
import com.springdemo.springbootdemo.entities.Account;
import com.springdemo.springbootdemo.validation.FieldMatch;
import com.springdemo.springbootdemo.validation.ValidEmail;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})

public class CrmPerson {
        @NotNull(message="is required")
        @Size(min=1, message="is required")
        private String username;

        @NotNull(message="is required")
        @Length(min = 7, message = "Password should be at least 7 characters long")
        private String password;

        @NotNull(message = "is required")
        @Size(min = 1, message = "is required")
        private String matchingPassword;

        @NotNull(message="is required")
        @Size(min = 1, message = "is required")
        private String firstName;

        @NotNull(message="is required")
        @Size(min = 1, message = "is required")
        private String lastName;

        @ValidEmail
        @NotNull(message="is required")
        @Size(min = 1, message = "is required")
        private String emailAddress;


    public String getPassword() {
        return password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

}
