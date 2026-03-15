package me.chromiumore.tigerbank.domain.param;

import lombok.Getter;
import lombok.Setter;
import me.chromiumore.tigerbank.domain.BankAccount;
import me.chromiumore.tigerbank.domain.Category;
import me.chromiumore.tigerbank.domain.OperationType;

import java.time.LocalDate;

@Getter @Setter
public class OperationParam extends EntityParam {
    private OperationType type;
    private BankAccount bankAccount;
    private double amount;
    private LocalDate date;
    private String description;
    private Category category;

    public OperationParam(OperationType type, BankAccount bankAccount, double amount, Category category, String description) {
        super(EntityType.OPERATION);
        this.type = type;
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }
}
