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
                .antMatchers(HttpMethod.GET, "/account/**").permitAll()
                .antMatchers(HttpMethod.POST, "/account/**").hasRole("employee")
                .antMatchers(HttpMethod.PUT, "/account/**").hasRole("employee")
                .antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.GET, "/users/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/**").hasRole("employee")
                .antMatchers(HttpMethod.POST, "/users/**").hasRole("employee")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .loginProcessingUrl("/performing_login")
                .defaultSuccessUrl("/index.html", true)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }
}
