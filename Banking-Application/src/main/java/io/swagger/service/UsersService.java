package io.swagger.service;

import io.swagger.api.UsersApiController;
import io.swagger.dao.UsersRepository;
import io.swagger.model.Account;
import io.swagger.model.AuthToken;
import io.swagger.model.SecurityUserDetails;
import io.swagger.model.Users;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    public boolean checkAuthorization(Long userId) {
        Object authDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users u = ((SecurityUserDetails) authDetails).getUser();

        if (!userId.equals(u.getId()) && ((SecurityUserDetails) authDetails).getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
            return false;
        }
        return true;
    }


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
