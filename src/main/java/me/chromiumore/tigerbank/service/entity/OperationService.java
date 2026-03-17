package me.chromiumore.tigerbank.service.entity;

import me.chromiumore.tigerbank.domain.BankAccount;
import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.Operation;
import me.chromiumore.tigerbank.domain.param.EntityParam;
import me.chromiumore.tigerbank.domain.param.OperationNoTypeParam;
import me.chromiumore.tigerbank.factory.OperationFactory;
import me.chromiumore.tigerbank.repository.AccountRepository;
import me.chromiumore.tigerbank.repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService implements EntityService {
    @Autowired
    private OperationsRepository operationsRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OperationFactory factory;

    @Override
    public BaseEntity get(int id) {
        return operationsRepository.get(id);
    }

    @Override
    public void update(int id, BaseEntity entity) {
        operationsRepository.update(id, entity);
    }

    @Override
    public void delete(int id) {
        operationsRepository.remove(id);
    }

    public int deposit(OperationNoTypeParam param) {
        Operation operation = factory.createIncomeOperation(param);
        int id = operationsRepository.add(operation);

        BankAccount account = (BankAccount) accountRepository.get(param.getBankAccountId());
        account.deposit(param.getAmount());

        return id;
    }

    public int withdraw(OperationNoTypeParam param) {
        Operation operation = factory.createExpenseOperation(param);
        int id = operationsRepository.add(operation);

        BankAccount account = (BankAccount) accountRepository.get(param.getBankAccountId());
        account.withdraw(param.getAmount());

        return id;
    }
}
