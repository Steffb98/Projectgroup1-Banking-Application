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
        //auth.userDetailsService(service);
        auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER")
                .and().withUser("1").password("{noop}1").roles("customer", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/account/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/account/{accountId}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/account/listaccount").hasAnyAuthority("USER", "ADMIN")

                .antMatchers("/transaction/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/transaction/{transactionId}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/transaction/account/{accountId}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/transaction/user/{userId}").hasAnyAuthority("USER", "ADMIN")

                .antMatchers("/**").permitAll()

                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/logout").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/users/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/users/{id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/users/search").hasAnyAuthority("USER", "ADMIN")
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .loginPage("/index.html")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/HomePage.html", false)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }
}
