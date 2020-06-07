package io.swagger.service;

import io.swagger.dao.UsersRepository;
import io.swagger.model.Account;
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

    public Boolean addUser(Users user) {
        Users userEmail = usersRepository.findByEmail(user.getEmail());
        if (userEmail != null){
            return false;
        }
        else{
            user.setTypeofuser(Users.TypeofuserEnum.CUSTOMER);
            usersRepository.save(user);
            return true;
        }
    }

    public Users GetUserByEmail(String email){
        return usersRepository.findByEmail(email);
    }

    public Users getUserById(Long id)
    {
        return usersRepository.findOne(id);
    }

    public void toggleUser(Long id)
    {
        try {
            Users u = usersRepository.findOne(id);
            if(u.isIsactive() == false){
                u.setIsactive(true);
            }
            else{
                u.setIsactive(false);
            }
            usersRepository.save(u);
        }catch(Exception io){

        }

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
