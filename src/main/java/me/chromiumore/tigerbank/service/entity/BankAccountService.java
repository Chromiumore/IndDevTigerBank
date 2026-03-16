package me.chromiumore.tigerbank.service.entity;

import me.chromiumore.tigerbank.domain.BankAccount;
import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.param.EntityParam;
import me.chromiumore.tigerbank.factory.BankAccountFactory;
import me.chromiumore.tigerbank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService implements EntityService {
    @Autowired
    AccountRepository repository;
    @Autowired
    BankAccountFactory factory;

    @Override
    public BaseEntity create(EntityParam param) {
        BankAccount account = (BankAccount) factory.createEntity(param);
        repository.add(account);
        return account;
    }

    @Override
    public BaseEntity get(int id) {
        return repository.get(id);
    }

    @Override
    public void update(int id, BaseEntity entity) {
        repository.update(id, entity);
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }
}
