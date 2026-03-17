package me.chromiumore.tigerbank.domain.param;

import lombok.Getter;
import lombok.Setter;
import me.chromiumore.tigerbank.domain.OperationType;

@Getter @Setter
public class OperationNoTypeParam extends EntityParam {
    protected int bankAccountId;
    protected double amount;
    protected String description;
    protected int categoryId;

    public OperationNoTypeParam(int bankAccountId, double amount, int categoryId, String description) {
        super(EntityType.OPERATION);
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.description = description;
    }
}
