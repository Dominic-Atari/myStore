package com.promineotech.mystore2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.promineotech.mystore2.service.AppUserService;

@Controller
public class HomeController {
    @Autowired
    private AppUserService appUserService;

    @GetMapping({"/", "/index"})
    public String home(Model model, Principal principal) {
        if (principal != null) {
            String firstName = appUserService.getLoggedInUserFirstName(principal);
            model.addAttribute("firstName", firstName);
        }
        return "index";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/message")
    public String message() {
        return "message";
    }
    
}