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
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/")
                .permitAll()
                // TODO: Authentication turned off for testing
                // TODO: Can't create mock authenticated user
                // TODO: Remove comments for authentication!
                /*.antMatchers(HttpMethod.GET, "/user/login").permitAll()
                .antMatchers(HttpMethod.GET, "/user/logout").hasAnyAuthority("EMPLOYEE", "CUSTOMER")
                .antMatchers(HttpMethod.GET, "/users").hasAnyAuthority("EMPLOYEE", "CUSTOMER")
                .antMatchers(HttpMethod.PUT, "/users").hasAnyAuthority("EMPLOYEE", "CUSTOMER")
                .antMatchers(HttpMethod.POST, "/users").hasAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/users/{id}").hasAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.PUT, "/users/{id}").hasAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/users/search").hasAuthority("EMPLOYEE")

                .antMatchers(HttpMethod.POST, "/account").hasAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.PUT, "/account/{accountId}").hasAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/account/{accountId}").hasAnyAuthority("EMPLOYEE", "CUSTOMER")
                .antMatchers(HttpMethod.GET, "/account/listaccount").hasAnyAuthority("EMPLOYEE", "CUSTOMER")

                .antMatchers(HttpMethod.GET, "/transaction").hasAnyAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.POST, "/transaction/{accountId}").hasAnyAuthority("EMPLOYEE", "CUSTOMER")
                .antMatchers(HttpMethod.GET, "/transaction/{transactionId}").hasAnyAuthority("EMPLOYEE", "CUSTOMER")
                .antMatchers(HttpMethod.GET, "/transaction/account/{accountId}").hasAnyAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/transaction/user/{userId}").hasAnyAuthority("EMPLOYEE", "CUSTOMER")

                .anyRequest().authenticated()*/

                .and()
                .formLogin().permitAll()
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/index.html", true)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }
}
