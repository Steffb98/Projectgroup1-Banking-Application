package io.swagger.configuration;

import io.swagger.dao.AccountRepository;
import io.swagger.dao.TransactionRepository;
import io.swagger.dao.UsersRepository;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.Users;
import org.apache.catalina.User;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

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
                new Users("Test", "Employee", "1", "1", true, Users.TypeofuserEnum.EMPLOYEE),
                new Users("Bobby", "McBobface", "bobbyface@gmail.com", "Sterk!", true, Users.TypeofuserEnum.CUSTOMER),
                new Users("Ba", "nq", "banq@inholland.nl", "Hfsuaidhfidou", false, Users.TypeofuserEnum.EMPLOYEE),
                new Users("user", "user", "user", "password", true, Users.TypeofuserEnum.CUSTOMER)
                );

        Account accountTestSaving = new Account(Account.TypeofaccountEnum.SAVING, new BigDecimal(-10.00), true,51L);
        Account accountTestDeposit = new Account(Account.TypeofaccountEnum.DEPOSIT, new BigDecimal(-10.00), true,51L);
        Account accountHayoSaving = new Account(Account.TypeofaccountEnum.SAVING, new BigDecimal(-10.00), true,50L);
        Account accountHayoDeposit = new Account(Account.TypeofaccountEnum.DEPOSIT, new BigDecimal(-10.00), true,50L);
        Account accountBobbySaving = new Account(Account.TypeofaccountEnum.SAVING, new BigDecimal(-10.00), true,52L);
        Account accountBobbyDeposit = new Account(Account.TypeofaccountEnum.DEPOSIT, new BigDecimal(-10.00), true,52L);

        Account bankAccount = new Account(Account.TypeofaccountEnum.SAVING, new BigDecimal(00.00), true, 53L);
        bankAccount.setIban("NL01 INHO 0000 0000 01");
        List<Account> accounts = Arrays.asList(accountTestSaving, accountTestDeposit, accountHayoSaving, accountHayoDeposit, accountBobbySaving, accountBobbyDeposit,bankAccount);

        List<Transaction> transactions = Arrays.asList(
                new Transaction(accountTestSaving, accountTestDeposit, new BigDecimal(10.00), users.get(0)),
                new Transaction(accountTestDeposit, accountTestSaving, new BigDecimal(20.00), users.get(1))
        );

        accountTestSaving.setBalance(new BigDecimal(30));
        accountTestDeposit.setBalance(new BigDecimal(300000));
        accountHayoSaving.setBalance(new BigDecimal(33));
        accountHayoDeposit.setBalance(new BigDecimal(85));
        accountBobbySaving.setBalance(new BigDecimal(124));
        accountBobbyDeposit.setBalance(new BigDecimal(750.73));


        users.forEach(usersRepository::save);
        accounts.forEach(accountRepository::save);
        transactions.forEach(transactionRepository::save);

        accountRepository.findAll().forEach(System.out::println);
        usersRepository.findAll().forEach(System.out::println);
        transactionRepository.findAll().forEach(System.out::println);
    }
}
