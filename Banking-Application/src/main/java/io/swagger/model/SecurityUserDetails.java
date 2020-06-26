package io.swagger.model;

import org.apache.tomcat.util.net.jsse.JSSEImplementation;
import org.springframework.security.access.method.P;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUserDetails implements UserDetails{
    private Users user;

    private String username;
    private String password;
    private Collection<SimpleGrantedAuthority> auth;
    private boolean active;

    public SecurityUserDetails(String username){
        this.username = username;
    }

    public SecurityUserDetails(Users user){
        this.user = user;
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.auth = new ArrayList<>();

        if (user.getTypeofuser().name() == "employee"){
            auth.add(new SimpleGrantedAuthority("ADMIN"));
        }
        else if (user.getTypeofuser().name() == "customer"){
            auth.add(new SimpleGrantedAuthority("USER"));
        }
        this.active = user.isIsactive();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auth;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public Users getUser(){
        return user;
    }
}
