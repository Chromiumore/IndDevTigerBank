package me.chromiumore.tigerbank.factory;

import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.Operation;
import me.chromiumore.tigerbank.domain.OperationType;
import me.chromiumore.tigerbank.domain.param.EntityParam;
import me.chromiumore.tigerbank.domain.param.OperationNoTypeParam;
import me.chromiumore.tigerbank.domain.param.OperationParam;
import me.chromiumore.tigerbank.exception.CreateEntityException;
import me.chromiumore.tigerbank.exception.UnsupportedParameterException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OperationFactory implements EntityFactory {
    @Override
    public BaseEntity createEntity(EntityParam param) throws CreateEntityException {
        if (param instanceof OperationParam operationParam) {
            return createEntityWithDate(operationParam, LocalDate.now());
        }

        throw new UnsupportedParameterException();
    }

    public BaseEntity createEntityWithDate(EntityParam param, LocalDate date) throws CreateEntityException {
        if (param instanceof OperationParam operationParam) {
            return new Operation(
                    operationParam.getType(),
                    operationParam.getBankAccountId(),
                    operationParam.getAmount(),
                    date,
                    operationParam.getDescription(),
                    operationParam.getCategoryId()
            );
        }

        throw new UnsupportedParameterException();
    }

    public Operation createExpenseOperation(OperationNoTypeParam param) {
        return new Operation(
                OperationType.EXPENSE,
                param.getBankAccountId(),
                param.getAmount(),
                LocalDate.now(),
                param.getDescription(),
                param.getCategoryId()
        );
    }

    public Operation createIncomeOperation(OperationNoTypeParam param) {
        return new Operation(
                OperationType.INCOME,
                param.getBankAccountId(),
                param.getAmount(),
                LocalDate.now(),
                param.getDescription(),
                param.getCategoryId()
        );
    }
}
