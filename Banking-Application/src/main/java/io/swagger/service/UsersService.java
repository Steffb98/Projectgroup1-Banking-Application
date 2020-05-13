package io.swagger.service;

import io.swagger.dao.UsersRepository;
import io.swagger.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getAllUsers() {
        return (List<Users>) usersRepository.findAll();
    }

    public void addUser(Users user) {
        usersRepository.save(user);
        System.out.println(user);
    }
}
