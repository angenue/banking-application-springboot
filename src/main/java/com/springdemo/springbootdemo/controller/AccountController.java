package com.springdemo.springbootdemo.controller;

import com.springdemo.springbootdemo.entities.Account;
import com.springdemo.springbootdemo.entities.Person;
import com.springdemo.springbootdemo.entities.TransactionDetails;
import com.springdemo.springbootdemo.service.AccountService;
import com.springdemo.springbootdemo.service.PersonService;
import com.springdemo.springbootdemo.service.TransactionService;
import com.springdemo.springbootdemo.user.CrmAccount;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class AccountController {

    private PersonService personService;
    private AccountService accountService;

    private TransactionService transactionService;

    public AccountController(PersonService personService, AccountService accountService,TransactionService transactionService) {
        this.personService = personService;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/account")
    public String getUserInfo(Principal principal, Model model){
        String name = principal.getName(); //get logged in username
        List<Account> accountList = accountService.findAccountsByUsername(name);
        //to list persons first name
        Person person = personService.findByUsernameIgnoreCase(name);


        model.addAttribute("person", person);
        model.addAttribute("account", accountList);
        model.addAttribute("addAccount", new CrmAccount());
        model.addAttribute("deposit", new CrmAccount());
        model.addAttribute("withdraw", new CrmAccount());
        return "/user/show-data";

    }

    //lists all transactions with associated account
    @GetMapping("/transactions")
    public String getTransactions(@RequestParam("accountNumber") int accountNumber, Model model) {
        //so the account that was called it displayed up top
        Account account = accountService.findById(accountNumber);

        List<TransactionDetails> transactionDetails = transactionService.findTransactionsByAccountNumber(accountNumber);

        model.addAttribute("account", account);
        model.addAttribute("transaction", transactionDetails);

        return "/user/transactions";
    }

    @PostMapping("/account")
    public String addAccount(@Valid @ModelAttribute("addAccount") CrmAccount theAccount,
                              Principal principal, Model model) {

        String name = principal.getName(); //to add username so account links to person

        personService.createAccount(name, theAccount);

        ModelAndView view = new ModelAndView("/user/show-data");
        view.addObject("addAccount", theAccount);

        // use a redirect to prevent duplicate submissions
        return "redirect:/user/account";
    }

    @PostMapping("/deposit")
    public String deposit(@ModelAttribute("deposit") CrmAccount theAccount, @Valid int accountNumberDeposit) {


        accountService.deposit(accountNumberDeposit, theAccount);
        //model.addAttribute("account", theAccount);

        return "redirect:/user/account";
    }

    @PostMapping("/withdraw")
    public String withdraw(@ModelAttribute("withdraw") CrmAccount theAccount, @Valid int accountNumberWithdraw) {

        accountService.withdraw(accountNumberWithdraw,theAccount);

        return "redirect:/user/account";
    }

    // deletes the users entire account
    @GetMapping("/delete")
    public String delete(@RequestParam("username") String username) {

        personService.deleteByUsername(username);

        // redirect to /login page
        return "redirect:/login?accountDeleted";

    }

    // deletes specific accounts (savings or chekcing)
    @GetMapping("/deleteAccount")
    public String deleteAccount(@RequestParam("accountNumber") int accountNumber) {

        // delete the employee
        accountService.deleteAccountByAccountNumber(accountNumber);

        // redirect to /employees/list
        return "redirect:/user/account";

    }

}
