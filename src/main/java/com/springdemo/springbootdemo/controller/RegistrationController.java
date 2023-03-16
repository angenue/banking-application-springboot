package com.springdemo.springbootdemo.controller;

import com.springdemo.springbootdemo.entities.Account;
import com.springdemo.springbootdemo.entities.Person;
import com.springdemo.springbootdemo.entities.TransactionDetails;
import com.springdemo.springbootdemo.entities.Type;
import com.springdemo.springbootdemo.service.PersonService;
import com.springdemo.springbootdemo.user.CrmAccount;
import com.springdemo.springbootdemo.user.CrmPerson;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final PersonService personService;

    private Logger logger = Logger.getLogger(getClass().getName());

    public RegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/registrationForm")
    public String login(Model model) {
        model.addAttribute("crmPerson", new CrmPerson());
        model.addAttribute("account", new CrmAccount());
        return "registration-form";
    }

    @PostMapping("/processRegistration")
    public String processRegistrationForm (
            @Valid @ModelAttribute("crmPerson") CrmPerson theCrmPerson,BindingResult bindingResult,
            @Valid @ModelAttribute("account") CrmAccount theAccount,
            BindingResult bindingResult2,
            Model model) {

        String username = theCrmPerson.getUsername();
        String email = theCrmPerson.getEmailAddress();
        logger.info("Processing registration form for: " + username);

        // form validation
        if (bindingResult.hasErrors() || bindingResult2.hasErrors()){
            return "registration-form";
        }

        Person existing = personService.findByUsernameIgnoreCase(username);
        Person emailExisting = personService.findByEmailAddressIgnoreCase(email);
        if(existing != null) {
            model.addAttribute("crmPerson", new CrmPerson());
            model.addAttribute("account", new Account());
            model.addAttribute("registrationError", "User already exists.");

            logger.warning("User already exists.");
            return "registration-form";
        }

        if(emailExisting != null) {
            model.addAttribute("crmPerson", new CrmPerson());
            model.addAttribute("account", new Account());
            model.addAttribute("registrationError", "Email already exists.");

            logger.warning("Email already exists.");
            return "registration-form";
        }

        //logger.info("Successfully created user: " + username);

        personService.registerAccount(theCrmPerson, theAccount);

        return "registration-confirmation";

        //return "account-form";
    }



}
