package io.swagger.configuration;

import io.swagger.dao.AccountRepository;
import io.swagger.model.Account;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ScheduledTasks {

    private static final Logger logger = Logger.getLogger(ScheduledTasks.class.getName());
    private final AccountRepository accountRepository;

    public ScheduledTasks(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Scheduled(cron = "0 1 * * * ?")
    public void resetAccountDayLimit(){
        logger.log(Level.INFO,"Resetting day limit...");
        for (Account account : accountRepository.findAll()){
            account.setNumberOfTransaction(0);
        }
    }
}