package com.padmapg.codefellowship.controllers;

import com.padmapg.codefellowship.models.ApplicationUser;
import com.padmapg.codefellowship.models.ApplicationUserRepository;
import com.padmapg.codefellowship.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class ApplicationUserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/users")
    public RedirectView createUser(String username, String password, String firstName, String lastName, String bio, Date dateOfBirth){
        ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password), firstName, lastName, bio, dateOfBirth);
        applicationUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/myprofile");
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/users/{id}")
    public String getSingleUser(@PathVariable long id, Model m, Principal p){
        ApplicationUser currentUser = applicationUserRepository.findById(id).get();
        m.addAttribute("currentUser", currentUser);
        m.addAttribute("user", p);
        return "singleuser";
    }

    @PostMapping("/users/{id}")
    public RedirectView addFollowing(@PathVariable long id, Model m, Principal p){
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        loggedInUser.addFollowing(applicationUserRepository.findById(id).get());
        applicationUserRepository.save(loggedInUser);
        return new RedirectView("/");
    }

    @GetMapping("/users")
    public String getAllUsers(Model m){
        List<ApplicationUser> allUsers = applicationUserRepository.findAll();
        m.addAttribute("allusers", allUsers);
        return "allUsers";

    }
    @GetMapping("/feed")
    public String getUsersPosts(Model m, Principal p){
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        Set<ApplicationUser> usersThatIFollow = loggedInUser.getUsersThatIFollow();
        List<Post> posts = new ArrayList<>();
        for (ApplicationUser user: usersThatIFollow){
            posts.addAll(user.getPosts());
        }
        m.addAttribute("feed", posts);
        m.addAttribute("user", p);
        return "feed";
    }
    @GetMapping("/myprofile")
    public String getMyProfile(Principal p, Model m){
        m.addAttribute("person", applicationUserRepository.findByUsername(p.getName()));
        m.addAttribute("user", p);
        return "myprofile";
    }
}
