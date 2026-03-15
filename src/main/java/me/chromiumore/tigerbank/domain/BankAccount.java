package me.chromiumore.tigerbank.domain;

import lombok.Getter;
import lombok.Setter;

public class BankAccount extends BaseEntity{
    private static int nextId = 0;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private double balance;

    public BankAccount(String name, double balance) {
        super(nextId);
        nextId++;
        this.name = name;
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
