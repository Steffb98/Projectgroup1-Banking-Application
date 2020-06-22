package io.swagger.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionTest {

    private Transaction transaction;

    @Before
    public void setUp() {
        transaction = new Transaction();
    }

    @Test
    public void createTransactionShouldNotBeNull() {
        assertNotNull(transaction);
    }
}