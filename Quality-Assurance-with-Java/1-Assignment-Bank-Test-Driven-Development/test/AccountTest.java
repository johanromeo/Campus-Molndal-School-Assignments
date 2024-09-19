package main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account sut;

    /**
     * Ensures that initialCash cant be set to <= 0
     */
    @Test
    public void cantSetInitialCashToNegative() {
        // Arrange
        double expected = 0;
        // Act
        sut = new Account(0, 1);
        // Assert
        assertEquals(expected, sut.getBalance());
    }

    /**
     * Ensures that accountNumber cant be set to <0
     */
    @Test
    public void cantSetAccountNumberToNegative() {
        // Arrange
        int expected = 1;
        // Act
        sut = new Account(0, 1);
        // Assert
        assertEquals(expected, sut.getAccountNumber());
    }

    /**
     * Ensures that you cant deposit <= 0
     */
    @Test
    public void cantDepositNegativeOrZeroAmountToBalance() {
        // Arrange
        sut = new Account(1, 1);
        double expected = 2;
        // Act
        sut.deposit(1);
        // Assert
        assertEquals(expected, sut.getBalance(), 0.1);
    }

    /**
     * Ensures you can deposit
     */
    @Test
    public void canDepositToBalance() {
        // Arrange
        sut = new Account(1,1);
        double expected = 11;
        // Act
        sut.deposit(10);
        // Assert
        assertEquals(expected, sut.getBalance(), 0.1);
    }

    /**
     * Ensures that you cant withdraw more money than you have.
     * You cant rob the bank, the bank robs you. . .
     */
    @Test
    public void cantWithdrawIfNotEnoughMoney() {
        // Arrange
        sut = new Account(11,1);
        double expected = 0;
        // Act
        sut.withdraw(11);
        // Assert
        assertEquals(expected,sut.getBalance(), 0.1);
    }

    /**
     * Ensures that you actually can withdraw
     */
    @Test
    void canWithdrawIfEnoughMoney() {
        // Arrange
        sut = new Account(13.37,1);
        double expected = 0.37;
        // Act
        sut.withdraw(13);
        // Assert
        assertEquals(expected, sut.getBalance(),0.1);
    }
}