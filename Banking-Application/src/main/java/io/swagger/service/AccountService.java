package io.swagger.service;

import io.swagger.api.AccountApiController;
import io.swagger.dao.AccountRepository;
import io.swagger.model.Account;
import io.swagger.model.SecurityUserDetails;
import io.swagger.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private static final Logger log = LoggerFactory.getLogger(AccountApiController.class);

    public boolean checkAuthorization(String iban){
        Account acc = getAccountByIban(iban);
        Object authDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users u = ((SecurityUserDetails) authDetails).getUser();

        if (! acc.getUserid().equals(u.getId()) && !((SecurityUserDetails) authDetails).getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
            return false;
        }
        return true;
    }

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void CreateAccount(Account account) {
        boolean duplicate = true;
        while (duplicate) {
            account.setIban(account.GenerateIBAN());
            duplicate = CheckIBANAvailability(account.getIban());
        }
        accountRepository.save(account);
    }

    public List<Account> getAccountsByUserId(Long userId) {
        return (List<Account>) accountRepository.findAccountsByUserid(userId);
    }

    private boolean CheckIBANAvailability(String iban){
        try{
            accountRepository.findById(iban);
            return false;
        }catch(Exception io){
            return true;
        }
    }

    public Account getAccountByIban(String iban) {
        return accountRepository.findById(iban).orElse(null);
    }

    public void ToggleAccountActivity(String iban) {
        Account account = accountRepository.findById(iban).orElse(null);
        if (account.getIsactive() == true){
            account.setIsactive(false);
        }
        else{
            account.setIsactive(true);
        }
        accountRepository.save(account);
    }

    public void updateAmount(Account account){
        accountRepository.save(account);
    }
}
