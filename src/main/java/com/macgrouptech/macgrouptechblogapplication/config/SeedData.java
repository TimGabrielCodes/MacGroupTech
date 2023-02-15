package com.macgrouptech.macgrouptechblogapplication.config;

import com.macgrouptech.macgrouptechblogapplication.models.Account;
import com.macgrouptech.macgrouptechblogapplication.models.Post;
import com.macgrouptech.macgrouptechblogapplication.services.AccountService;
import com.macgrouptech.macgrouptechblogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;



    @Override
    public void run(String... args) throws Exception{
        List<Post> posts = postService.getAll();

        if (posts.size() == 0){
            Account account1 = new Account();
            Account account2 = new Account();

            account1.setFirstName("Tim");
            account1.setLastName("Admin");
            account1.setEmail("timothy@macgrouptech.com");
            account1.setPassword("password");

            account2.setFirstName("Tim");
            account2.setLastName("User");
            account2.setEmail("user@macgrouptech.com");
            account2.setPassword("password");

            accountService.save(account1);
            accountService.save(account2);

            Post post1 = new Post();
            post1.setTitle("Title of First Post");
            post1.setBody("This is the Body of the first Post");
            post1.setAccount(account1);

            Post post2 = new Post();
            post2.setTitle("This is the title of post2 ");
            post2.setBody("This is the body of post 2");
            post2.setAccount(account2);

            postService.save(post1);
            postService.save(post2);

        }



    }

}
