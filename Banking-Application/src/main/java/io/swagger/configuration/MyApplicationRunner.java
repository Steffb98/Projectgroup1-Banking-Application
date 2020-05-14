package io.swagger.configuration;

import io.swagger.dao.AccountRepository;
import io.swagger.dao.TransactionRepository;
import io.swagger.dao.UsersRepository;
import io.swagger.model.Account;
import io.swagger.model.Users;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private TransactionRepository transactionRepository;
    private UsersRepository usersRepository;
    private AccountRepository accountRepository;

    public MyApplicationRunner(TransactionRepository transactionRepository, UsersRepository usersRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.usersRepository = usersRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        List<Users> users = Arrays.asList(
                new Users("Hayo", "Bos", "663143@student.inholland.nl", "wachtwoord"),
                new Users("Bobby", "McBobface", "bobbyface@gmail.com", "Sterk")
        );

        Account account = new Account("abc", new BigDecimal("10.00"), Account.TypeofaccountEnum.SAVING);
        Account account2 = new Account("abc2", new BigDecimal("11.00"), Account.TypeofaccountEnum.DEPOSIT);
        List<Account> accounts = Arrays.asList(account, account2);

        users.forEach(usersRepository::save);
        accounts.forEach(accountRepository::save);

        accountRepository.findAll().forEach(System.out::println);
        usersRepository.findAll().forEach(System.out::println);
    }
}
