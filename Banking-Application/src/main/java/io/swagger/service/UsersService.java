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

    public Users getUserById(Long id)
    {
        return usersRepository.findOne(id);
    }

    public void toggleUser(Long id)
    {
        Users u = usersRepository.findOne(id);
        if(u.isIsactive() == false){
            u.setIsactive(true);
        }else{
            u.setIsactive(false);
        }
    }

    public void updateUser(Long id, String email, String password)
    {
        Users user = usersRepository.findOne(id);
        user.setEmail(email);
        user.setPassword(password);
    }

    public Users login(String email, String password){

        return usersRepository.findUserByEmailAndPassword(email,password);

    }
}
