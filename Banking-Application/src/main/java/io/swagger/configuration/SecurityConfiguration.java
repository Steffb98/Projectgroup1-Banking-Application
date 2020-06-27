package io.swagger.configuration;

import io.swagger.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl service;

    public SecurityConfiguration(UserDetailsServiceImpl service) {
        this.service = service;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(service);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/logout").hasAnyAuthority("EMPLOYEE", "CUSTOMER")
                .antMatchers("/users/**").hasAnyAuthority("EMPLOYEE", "CUSTOMER")
                .antMatchers("/users/{id}").hasAnyAuthority("EMPLOYEE")
                .antMatchers("/users/search").hasAnyAuthority("EMPLOYEE")

                .antMatchers("/account/**").hasAnyAuthority("EMPLOYEE", "CUSTOMER")
                .antMatchers("/account/{accountId}").hasAnyAuthority("EMPLOYEE", "CUSTOMER")
                .antMatchers("/account/listaccount").hasAnyAuthority("EMPLOYEE", "CUSTOMER")

                .antMatchers("/transaction/**").hasAnyAuthority("EMPLOYEE", "CUSTOMER")
                .antMatchers("/transaction/{transactionId}").hasAnyAuthority("EMPLOYEE", "CUSTOMER")
                .antMatchers("/transaction/account/{accountId}").hasAnyAuthority("EMPLOYEE", "CUSTOMER")
                .antMatchers("/transaction/user/{userId}").hasAnyAuthority("EMPLOYEE", "CUSTOMER")

                .anyRequest().authenticated()

                .and()
                .formLogin().permitAll()
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/index.html")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }
}
