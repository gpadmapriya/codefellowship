package com.padmapg.codefellowship.controllers;

import com.padmapg.codefellowship.models.ApplicationUser;
import com.padmapg.codefellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @GetMapping("/")
    public String getRoot(Principal p, Model m){
        ApplicationUser applicationUser = null;
        if(p != null){
            applicationUser=applicationUserRepository.findByUsername(p.getName());
            m.addAttribute("user", p);
            m.addAttribute("person", applicationUser);
            return "myprofile";
        } else {
            return "root";
        }

    }

    @GetMapping("/signup")
    public String getSignup(){
        return "registration";
    }

    @RequestMapping("/")
    public void handleError(){
        throw new RuntimeException("exception");
    }

}
