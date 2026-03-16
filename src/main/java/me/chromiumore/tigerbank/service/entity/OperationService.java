package me.chromiumore.tigerbank.service.entity;

import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.Operation;
import me.chromiumore.tigerbank.domain.param.EntityParam;
import me.chromiumore.tigerbank.factory.OperationFactory;
import me.chromiumore.tigerbank.repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService implements EntityService {
    @Autowired
    private OperationsRepository repository;
    @Autowired
    private OperationFactory factory;

    @Override
    public int create(EntityParam param) {
        Operation operation = (Operation) factory.createEntity(param);
        return repository.add(operation);
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
