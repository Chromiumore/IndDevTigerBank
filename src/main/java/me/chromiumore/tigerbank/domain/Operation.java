package me.chromiumore.tigerbank.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class Operation extends BaseEntity {
    @Getter @Setter
    private OperationType type;
    @Getter @Setter
    private BankAccount bankAccount;
    @Getter @Setter
    private double amount;
    @Getter @Setter
    private LocalDate date;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private Category category;

    public Operation(OperationType type, BankAccount bankAccount, double amount, Category category, String description) {
        this.type = type;
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = LocalDate.now();
    }
}
