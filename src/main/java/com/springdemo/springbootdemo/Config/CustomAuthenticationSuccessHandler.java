package com.springdemo.springbootdemo.Config;

import com.springdemo.springbootdemo.entities.Person;
import com.springdemo.springbootdemo.service.PersonService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private PersonService personService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String username = authentication.getName();
        System.out.println("Username = " + username);
        Person person = personService.findByUsernameIgnoreCase(username);

        HttpSession session = request.getSession();
        session.setAttribute("user", person);

        response.sendRedirect(request.getContextPath() + "/");
    }
}
