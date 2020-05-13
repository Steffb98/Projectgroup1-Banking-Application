package io.swagger.configuration;

import io.swagger.dao.AccountRepository;
import io.swagger.dao.TransactionRepository;
import io.swagger.dao.UsersRepository;
import io.swagger.model.Users;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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

        users.forEach(usersRepository::save);

        usersRepository.findAll().forEach(System.out::println);

    }
}
