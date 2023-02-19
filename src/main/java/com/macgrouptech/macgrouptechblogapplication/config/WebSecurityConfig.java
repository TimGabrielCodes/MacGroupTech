package com.macgrouptech.macgrouptechblogapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {

    private static final String[] WHITELIST = {
            "/",
            "/register",
            "/h2-console/*",


    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers(WHITELIST).permitAll()
                .antMatchers(HttpMethod.GET, "/posts/*").permitAll()
                .anyRequest().authenticated();

        http
                .formLogin()
                        .loginPage("/login")
                                .loginProcessingUrl("/login")
                                        .usernameParameter("email")
                                                .passwordParameter("password")
                                                        .defaultSuccessUrl("/", true)
                                                                .failureUrl("/login?error")
                                                                        .permitAll()
                                                                                .and()
                                                                                        .logout()
                                                                                                .logoutUrl("/logout")
                                                                                                        .logoutSuccessUrl("/login?logout")
                                                                                                                .and()
                                                                                                                        .httpBasic();

        //TODO: Remove these when I move away from h2 console
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();

    }
}
