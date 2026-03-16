package me.chromiumore.tigerbank.domain.param;

import lombok.Getter;
import lombok.Setter;
import me.chromiumore.tigerbank.domain.Category;
import me.chromiumore.tigerbank.domain.OperationType;

import java.time.LocalDate;

@Getter @Setter
public class OperationParam extends EntityParam {
    private OperationType type;
    private int bankAccountId;
    private double amount;
    private LocalDate date;
    private String description;
    private int categoryId;

    public OperationParam(OperationType type, int bankAccountId, double amount, int categoryId, String description) {
        super(EntityType.OPERATION);
        this.type = type;
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.description = description;
    }
}
