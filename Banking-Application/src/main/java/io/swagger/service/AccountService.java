package io.swagger.service;

import io.swagger.dao.AccountRepository;
import io.swagger.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void CreateAccount(Account account) {
        boolean duplicate = true;
        while (duplicate == true) {
            account.setIban(account.GenerateIBAN());
            duplicate = CheckIBAN(account.getIban());
        }
        accountRepository.save(account);
    }

    public List<Account> getAccountsByUserId(Long userId) {
        return (List<Account>) accountRepository.findAccountsByUserid(userId);
    }

    private boolean CheckIBAN(String iban){
        try{
            accountRepository.findOne(iban);
            return false;
        }catch(Exception io){
            return true;
        }
    }

    public Account getAccountsByIban(String iban) {
        return accountRepository.findOne(iban);
    }

    public void ToggleActivity(String iban) {
        try {
            Account account = accountRepository.findOne(iban);
            if (account.getIsactive() == true){
                account.setIsactive(false);
            }
            else{
                account.setIsactive(true);
            }
            accountRepository.save(account);
        }catch(Exception io){

        }
    }

    public void updateAmount(Account account){
        accountRepository.save(account);
    }
}
