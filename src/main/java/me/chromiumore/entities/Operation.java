package me.chromiumore.entities;

import java.util.Date;

public class Operation extends BaseEntity {
    private static int nextId = 0;
    private OperationType type;
    private int bankAccountId;
    private double amount;
    private Date date;
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
    }
}
