package me.chromiumore.tigerbank.factory;

import me.chromiumore.tigerbank.domain.BankAccount;
import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.param.BankAccountParam;
import me.chromiumore.tigerbank.domain.param.EntityParam;
import me.chromiumore.tigerbank.exception.CreateEntityException;
import me.chromiumore.tigerbank.exception.UnsupportedParameterException;
import org.springframework.stereotype.Service;

@Service
public class BankAccountFactory implements EntityFactory{
    @Override
    public BaseEntity createEntity(EntityParam param) throws CreateEntityException {
        if (param instanceof BankAccountParam accountParam) {
            return new BankAccount(
                    accountParam.getName(),
                    accountParam.getBalance()
            );
        }

        throw new UnsupportedParameterException();
    }
}
