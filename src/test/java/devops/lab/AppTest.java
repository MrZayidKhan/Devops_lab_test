package devops.lab;

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
        assertEquals(110.0, account.getBalance(), 0.001); 
    }

    @Test
    void testAccountBalanceCannotBeNegative() {
        // Attempt to withdraw $150 from a $100 balance
        account.withdraw(150.0); 
        
        // TEST FAILURE: We assert that the bank account should never allow a negative balance.
        // However, because App.java lacks the overdraft check, getBalance() returns -50.0, 
        // causing this assertion to fail.
        assertTrue(account.getBalance() >= 0.0, "Failure: Account balance has dropped below zero ($" + account.getBalance() + ")");
    }

    @Test
    void testZeroOrNegativeTransactionAmounts() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-10.0));

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-20.0));
        
        assertThrows(IllegalArgumentException.class, () -> new App(-50.0));
    }
}