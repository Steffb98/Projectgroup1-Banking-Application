package io.swagger.configuration;

import io.swagger.dao.AccountRepository;
import io.swagger.dao.TransactionRepository;
import io.swagger.dao.UsersRepository;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.Users;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
                new Users("Hayo", "Bos", "663143@student.inholland.nl", "wachtwoord", true, Users.TypeofuserEnum.EMPLOYEE),
                new Users("Hayo", "Bos", "1", "1", true, Users.TypeofuserEnum.EMPLOYEE),
                new Users("Bobby", "McBobface", "bobbyface@gmail.com", "Sterk!", true, Users.TypeofuserEnum.CUSTOMER)
        );

        Account account = new Account(Account.TypeofaccountEnum.SAVING, new BigDecimal(-10.00), true,1L);
        Account account2 = new Account(Account.TypeofaccountEnum.DEPOSIT, new BigDecimal(-10.00), true,2L);
        List<Account> accounts = Arrays.asList(account, account2);

        List<Transaction> transactions = Arrays.asList(
                new Transaction(account, account2, new BigDecimal(10.00), users.get(0)),
                new Transaction(account2, account, new BigDecimal(20.00), users.get(1))
        );

        users.forEach(usersRepository::save);
        accounts.forEach(accountRepository::save);
        transactions.forEach(transactionRepository::save);

        accountRepository.findAll().forEach(System.out::println);
        usersRepository.findAll().forEach(System.out::println);
        transactionRepository.findAll().forEach(System.out::println);

        System.out.println("----------------");
        System.out.println(usersRepository.getAllByFirstname("Hayo"));


        //System.out.println(accountRepository);
    }
}
