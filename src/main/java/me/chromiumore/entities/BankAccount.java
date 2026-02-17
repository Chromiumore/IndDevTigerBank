package me.chromiumore.entities;

public class BankAccount extends BaseEntity{
    private static int nextId = 0;
    private String name;
    private double balance;

    public BankAccount(String name, double balance) {
        super(nextId);
        nextId++;
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > this.balance) {
            throw new RuntimeException("Недостаточно средств на счете");
        }
        this.balance -= amount;
    }
}
