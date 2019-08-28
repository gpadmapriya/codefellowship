package com.padmapg.codefellowship.controllers;

import com.padmapg.codefellowship.models.ApplicationUser;
import com.padmapg.codefellowship.models.ApplicationUserRepository;
import com.padmapg.codefellowship.models.Post;
import com.padmapg.codefellowship.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;

@Controller
public class PostController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    PostRepository postRepository;

    @GetMapping("/post/create")
    public String getPostCreation(Principal p, Model m){
        m.addAttribute("user", p);
        return "create";
    }

    @PostMapping("/post/create")
    public RedirectView createPost(String body, Principal p){
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Post newPost = new Post(body, createdAt, currentUser);
        postRepository.save(newPost);
        return new RedirectView("/users/" + currentUser.getId());
    }
}
