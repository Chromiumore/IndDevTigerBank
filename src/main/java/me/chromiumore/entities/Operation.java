package me.chromiumore.entities;

import java.time.LocalDate;

public class Operation extends BaseEntity {
    private static int nextId = 0;
    private OperationType type;
    private int bankAccountId;
    private double amount;
    private LocalDate date;
    private String description;
    private int categoryId;

    public Operation(OperationType type, int bankAccountId, double amount, int categoryId, String description) {
        super(nextId);
        nextId++;
        this.type = type;
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.description = description;
        this.date = LocalDate.now();
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
