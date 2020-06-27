package io.swagger.service;

import io.swagger.api.UsersApiController;
import io.swagger.dao.UsersRepository;
import io.swagger.model.SecurityUserDetails;
import io.swagger.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
        user.setTypeofuser(Users.TypeofuserEnum.CUSTOMER);
        usersRepository.save(user);
    }
    public boolean checkIfEmailExist(Users user){
        Users userEmail = usersRepository.findByEmail(user.getEmail());
        if(userEmail != null){
            return false;
        }else{
            return true;
        }
    }
    public Users GetUserByEmail(String email){
        return usersRepository.findByEmail(email);
    }

    public Users getUserById(Long id)
    {
        return usersRepository.findById(id).orElse(null);
    }

    public void switchUserStatus(Long id)
    {
        Users u = usersRepository.findById(id).orElse(null);
        if(u.isIsactive() == false){
            u.setIsactive(true);
        }
        else{
            u.setIsactive(false);
        }
        usersRepository.save(u);
    }

    public void updateUser(Long id, String email, String password)
    {
        Users user = usersRepository.findById(id).orElse(null);
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
