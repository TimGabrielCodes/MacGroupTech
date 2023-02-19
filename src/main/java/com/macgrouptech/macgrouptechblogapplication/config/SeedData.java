package com.macgrouptech.macgrouptechblogapplication.config;

import com.macgrouptech.macgrouptechblogapplication.models.Account;
import com.macgrouptech.macgrouptechblogapplication.models.Authority;
import com.macgrouptech.macgrouptechblogapplication.models.Post;
import com.macgrouptech.macgrouptechblogapplication.repositories.AuthorityRepository;
import com.macgrouptech.macgrouptechblogapplication.services.AccountService;
import com.macgrouptech.macgrouptechblogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;



    @Override
    public void run(String... args) throws Exception{
        List<Post> posts = postService.getAll();

        if (posts.size() == 0){

            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            Account account1 = new Account();
            Account account2 = new Account();

            account1.setFirstName("Tim");
            account1.setLastName("Admin");
            account1.setEmail("timothy@macgrouptech.com");
            account1.setPassword("password");
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);

            account2.setFirstName("Tim");
            account2.setLastName("User");
            account2.setEmail("user@macgrouptech.com");
            account2.setPassword("password");
            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            account1.setAuthorities(authorities2);


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
