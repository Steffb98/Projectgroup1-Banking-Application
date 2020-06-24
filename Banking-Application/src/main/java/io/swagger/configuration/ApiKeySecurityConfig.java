package io.swagger.configuration;

import io.swagger.dao.ApiKeyRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.*;

@Configuration
@EnableWebSecurity
@Log
public class ApiKeySecurityConfig extends WebSecurityConfigurerAdapter{

    private ApiKeyRepository repository;

    public ApiKeySecurityConfig(ApiKeyRepository repository){
        //log.info("Security configured");
        this.repository = repository;
    }

    //???????????????????????
    @Value("${X-AUTHTOKEN}")
    private String headerName;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}password") // {noop} means that password is being sent without encoding
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{noop}password")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ApiKeyAuthFilter filter = new ApiKeyAuthFilter(headerName);
        filter.setAuthenticationManager(authentication -> {
            String principle = (String) authentication.getPrincipal();

            if (repository.findById(principle) == null){
                throw new BadCredentialsException("Api key was not found in the system");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });

        http
            .antMatcher("/account/**")
            .csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(filter)
            .authorizeRequests()
            .anyRequest()
            .authenticated();
}
}
