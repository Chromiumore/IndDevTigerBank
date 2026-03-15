package me.chromiumore.tigerbank.factory;

import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.Operation;
import me.chromiumore.tigerbank.domain.param.EntityParam;
import me.chromiumore.tigerbank.domain.param.OperationParam;
import me.chromiumore.tigerbank.exception.CreateEntityException;
import me.chromiumore.tigerbank.exception.UnsupportedParameterException;
import org.springframework.stereotype.Service;

@Service
public class OperationFactory implements EntityFactory {
    @Override
    public BaseEntity createEntity(EntityParam param) throws CreateEntityException {
        if (param instanceof OperationParam operationParam) {
            return new Operation(
                    operationParam.getType(),
                    operationParam.getBankAccount().getId(),
                    operationParam.getAmount(),
                    operationParam.getCategory().getId(),
                    operationParam.getDescription()
            );
        }

        throw new UnsupportedParameterException();
    }
}
