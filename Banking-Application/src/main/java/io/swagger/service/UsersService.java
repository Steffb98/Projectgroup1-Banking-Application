package io.swagger.service;

import io.swagger.dao.UsersRepository;
import io.swagger.model.Account;
import io.swagger.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getAllUsers() {
        return (List<Users>) usersRepository.findAll();
    }

    public Users addUser(Users user) {
        user.setTypeofuser(Users.TypeofuserEnum.CUSTOMER);
        return usersRepository.save(user);
    }
    public boolean checkIfEmailExist(Users user){
        Users userEmail = usersRepository.findByEmail(user.getEmail());
        return userEmail == null;
    }
    public Users GetUserByEmail(String email){
        return usersRepository.findByEmail(email);
    }

    public Users getUserById(Long id)
    {
        return usersRepository.findOne(id);
    }

    public void switchUserStatus(Long id)
    {
        Users u = usersRepository.findOne(id);
        u.setIsactive(u.isIsactive() == false);
        usersRepository.save(u);
    }

    public void updateUser(Long id, String email, String password)
    {
        Users user = usersRepository.findOne(id);
        user.setEmail(email);
        user.setPassword(password);
        usersRepository.save(user);
    }

    public Users login(String email, String password){
        return usersRepository.findUserByEmailAndPassword(email,password);
    }

    public List<Users> getUserByName(String name){
        return (List<Users>) usersRepository.GetUserByName(name);
    }
}
