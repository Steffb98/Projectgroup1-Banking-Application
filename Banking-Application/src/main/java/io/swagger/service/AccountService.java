package io.swagger.service;

import io.swagger.dao.AccountRepository;
import io.swagger.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

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
        return accountRepository.findAccountsByUserid(userId);
    }

    private boolean CheckIBANAvailability(String iban){
        try{
            accountRepository.findOne(iban);
            return false;
        }catch(Exception io){
            return true;
        }
    }

    public Account getAccountByIban(String iban) {
        return accountRepository.findOne(iban);
    }

    public void ToggleAccountActivity(String iban) {
        Account account = accountRepository.findOne(iban);
        account.setIsactive(account.getIsactive() != true);
        accountRepository.save(account);
    }

    public void updateAccount(Account account){
        accountRepository.save(account);
    }
}
