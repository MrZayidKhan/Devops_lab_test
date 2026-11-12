package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private App account;

    @BeforeEach
    void setUp() {
        account = new App(100.0); // Starts with $100 for each test
    }

    @Test
    void testSuccessfulDepositAndWithdrawal() {
        account.deposit(50.0);
        account.withdraw(40.0);
        
        // INTENTIONAL FAILURE: Expecting 999.0 instead of the actual 110.0
        assertEquals(999.0, account.getBalance(), 0.001); 
    }

    @Test
    void testInsufficientBalanceWithdrawal() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(150.0); // More than current balance ($100)
        });
        assertEquals(100.0, account.getBalance(), 0.001);
    }

    @Test
    void testZeroOrNegativeTransactionAmounts() {
        // Zero or negative deposits
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-10.0));

        // Zero or negative withdrawals
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-20.0));
        
        // Negative initial balance
        assertThrows(IllegalArgumentException.class, () -> new App(-50.0));
    }

    @Test
    void testBalanceAfterMultipleTransactions() {
        account.deposit(200.0); // 100 + 200 = 300
        account.withdraw(50.0);  // 300 - 50 = 250
        account.deposit(50.0);   // 250 + 50 = 300
        account.withdraw(100.0); // 300 - 100 = 200

        assertEquals(200.0, account.getBalance(), 0.001);
    }
}