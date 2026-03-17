package me.chromiumore.tigerbank.domain.param;

import lombok.Getter;
import lombok.Setter;
import me.chromiumore.tigerbank.domain.Category;
import me.chromiumore.tigerbank.domain.OperationType;

import java.time.LocalDate;

@Getter @Setter
public class OperationParam extends OperationNoTypeParam {
    private OperationType type;

    public OperationParam(OperationType type, int bankAccountId, double amount, int categoryId, String description) {
        super(bankAccountId, amount, categoryId, description);
        this.type = type;
    }
}
