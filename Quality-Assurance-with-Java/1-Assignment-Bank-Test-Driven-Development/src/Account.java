package main;

public class Account {

    private double balance;
    private final int accountNumber;

    /**
     * @param initialCash   sets initial balance to the account. Mustn't be < 0
     * @param accountNumber sets initial account number. Mustn't be <= 0
     * @throws IllegalArgumentException if requirements are not met, arguments be thrown
     */
    public Account(double initialCash, int accountNumber) {
        if (initialCash < 0)
            throw new IllegalArgumentException("You can't open a bank account with a negative amount");
        else
            this.balance = initialCash;

        if (accountNumber <= 0)
            throw new IllegalArgumentException("Account number cant be <= 0");
        else
            this.accountNumber = accountNumber;

    }

    /**
     * @param cash deposit cash to your bank account. Mustn't be <= 0
     * @throws IllegalArgumentException if requirement is not met, arguments be thrown
     */
    public void deposit(double cash) {
        if (cash <= 0) throw new IllegalArgumentException("Can't deposit <= 0 money");
        else
            this.balance += cash;
    }

    /**
     * @param cash withdraw cash from bank account. Must not withdraw more than you have
     * @throws IllegalArgumentException if requirement is not met, arguments be thrown
     */
    public void withdraw(double cash) {
        if (cash > balance) throw new IllegalArgumentException("You can't rob the bank. The bank robs you!");
        else
            this.balance -= cash;
    }

    /**
     * @return returning current balance in bank account
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @return returning the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }
}
