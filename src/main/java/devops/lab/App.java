package devops.lab;

public class App {
    private double balance;

    // 1. Create a bank account with an initial balance
    public App(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
    }

    // 2. Deposit money into the account
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    // 3. Withdraw money from the account
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }

    // 4. Display/Get the current account balance
    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        App account = new App(100.0);
        account.deposit(50.0);
        account.withdraw(30.0);
        System.out.println("Current Balance: $" + account.getBalance());
    }
}