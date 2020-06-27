package io.swagger.service;

import io.swagger.dao.UsersRepository;
import io.swagger.model.SecurityUserDetails;
import io.swagger.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = Optional.ofNullable(usersRepository.findByEmail(email)).orElseThrow(
                () -> new UsernameNotFoundException("User " + email + "not found!"));
        return new SecurityUserDetails(user);
    }
}
