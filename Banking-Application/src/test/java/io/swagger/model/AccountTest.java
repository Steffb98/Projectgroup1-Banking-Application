package io.swagger.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {
    private Account account;

    @Before
    public void setup(){
        account = new Account();
    }

    @Test
    public void createUsersShouldNotBeNull(){assertNotNull(account);}

    @Test
    public void settingBalanceBelowMinimumBalanceShouldThrowException(){
        account.setMinimumbalance(new BigDecimal(-10.00));
        account.setBalance(new BigDecimal(10));
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> account.setBalance(new BigDecimal(-100)));
        assertEquals("Balance cannot be below the minimum balance", exception.getMessage());
    }

    @Test
    public void settingMinimumBalanceAboveZeroShouldThrowException(){
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> account.setMinimumbalance(new BigDecimal(1)));
        assertEquals("Minimum balance cannot be above zero", exception.getMessage());
    }

    @Test
    public void settingDayLimitBelowZeroShouldThrowException(){
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> account.setDayLimit(-1));
        assertEquals("DayLimit cannot be below zero", exception.getMessage());
    }

    @Test
    public void settingTransactionLimitBelowZeroShouldThrowException(){
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> account.setTransactionLimit(new BigDecimal(-1)));
        assertEquals("TransactionLimit cannot be below zero", exception.getMessage());
    }

    @Test
    public void settingNumberOfTransactionBelowZeroShouldThrowException(){
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> account.setNumberOfTransaction(-1));
        assertEquals("NumberOfTransaction cannot be below zero", exception.getMessage());
    }


}
