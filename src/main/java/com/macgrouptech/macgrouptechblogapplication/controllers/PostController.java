package com.macgrouptech.macgrouptechblogapplication.controllers;

import com.macgrouptech.macgrouptechblogapplication.models.Account;
import com.macgrouptech.macgrouptechblogapplication.models.Post;
import com.macgrouptech.macgrouptechblogapplication.services.AccountService;
import com.macgrouptech.macgrouptechblogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model){
        //find the post by id
        Optional<Post> optionalPost = postService.getById(id);

        if (optionalPost.isPresent()){
            Post post = optionalPost.get();
            model.addAttribute("post" , post);
            return "post";
        }else{
            return "404";
        }
    }

    @GetMapping("/posts/new")
    public String createNewPost (Model model){
        Optional<Account> optionalAccount = accountService.findByEmail("tim@macgrouptech.com");
        if (optionalAccount.isPresent()){
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post", post);
            return "post_new";
        } else{
            return "404";
        }
    }


}
