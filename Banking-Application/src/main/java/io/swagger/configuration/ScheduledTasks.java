package io.swagger.configuration;

import io.swagger.dao.AccountRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ScheduledTasks {

    private static final Logger logger = Logger.getLogger(ScheduledTasks.class.getName());
    private AccountRepository accountRepository;

    public ScheduledTasks(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Scheduled(cron = "0 1 * * * ?")
    public void resetAccountDayLimit(){
        logger.log(Level.INFO,"Resetting day limit.");
        //accountRepository.findAll().forEach(dayLimit = 0);
        
    }

    @Scheduled(cron = "0 0 * * 0 ?")
    public void resetAccountWeekLimit(){
        logger.log(Level.INFO,"Resetting weeek limit.");
        //accountRepository.findAll().forEach(weekLimit = 0);
    }


}
