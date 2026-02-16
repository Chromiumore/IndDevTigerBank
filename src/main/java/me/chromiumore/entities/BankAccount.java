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
}
