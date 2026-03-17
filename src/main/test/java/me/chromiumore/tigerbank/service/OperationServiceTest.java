package me.chromiumore.tigerbank.service;

import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.Operation;
import me.chromiumore.tigerbank.domain.OperationType;
import me.chromiumore.tigerbank.domain.param.OperationParam;
import me.chromiumore.tigerbank.factory.OperationFactory;
import me.chromiumore.tigerbank.repository.OperationsRepository;
import me.chromiumore.tigerbank.service.entity.OperationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@DisplayName("Работа сервиса операций")
public class OperationServiceTest {
    @Autowired
    private OperationService service;
    @Autowired
    private OperationsRepository repository;
    @Autowired
    OperationFactory factory;

    @Test
    @DisplayName("Добавление операции через сервис")
    public void addOperationTest() {
        OperationParam param = new OperationParam(
                OperationType.INCOME,
                0,
                400,
                0,
                "Add"
        );
        int id = service.create(param);

        BaseEntity entity = repository.get(id);
        assertNotNull(entity);
        assertInstanceOf(Operation.class, entity);

        Operation operation = (Operation) entity;
        assertEquals(param.getType(), operation.getType());
        assertEquals(param.getBankAccountId(), operation.getBankAccountId());
        assertEquals(param.getAmount(), operation.getAmount());
        assertEquals(param.getCategoryId(), operation.getCategoryId());
        assertEquals(param.getDescription(), operation.getDescription());
    }

    @Test
    @DisplayName("Редактирование операции через сервис")
    public void updateOperationTest() {
        OperationParam param = new OperationParam(
                OperationType.INCOME,
                0,
                800,
                0,
                "Old"
        );
        int id = service.create(param);
        assertNotNull(repository.get(id));

        Operation operation = (Operation) factory.createEntity(
                new OperationParam(
                        OperationType.EXPENSE,
                        0,
                        450,
                        0,
                        "Update"
                )
        );
        service.update(id, operation);

        BaseEntity updatedEntity = repository.get(id);

        assertInstanceOf(Operation.class, updatedEntity);
        Operation updatedOperation = (Operation) updatedEntity;

        assertEquals(operation.getType(), updatedOperation.getType());
        assertEquals(operation.getBankAccountId(), updatedOperation.getBankAccountId());
        assertEquals(operation.getAmount(), updatedOperation.getAmount());
        assertEquals(operation.getCategoryId(), updatedOperation.getCategoryId());
        assertEquals(operation.getDescription(), updatedOperation.getDescription());
    }

    @Test
    @DisplayName("Получение операции через сервис")
    public void getOperationTest() {
        OperationParam param = new OperationParam(
                OperationType.EXPENSE,
                0,
                14040.5,
                0,
                "Get"
        );
        int id = service.create(param);

        BaseEntity entity = service.get(id);
        assertNotNull(entity);
        assertInstanceOf(Operation.class, entity);

        Operation operation = (Operation) entity;
        assertEquals(param.getType(), operation.getType());
        assertEquals(param.getBankAccountId(), operation.getBankAccountId());
        assertEquals(param.getAmount(), operation.getAmount());
        assertEquals(param.getCategoryId(), operation.getCategoryId());
        assertEquals(param.getDescription(), operation.getDescription());
    }

    @Test
    @DisplayName("Удаление операции через сервис")
    public void deleteOperationTest() {
        OperationParam param = new OperationParam(
                OperationType.INCOME,
                0,
                0,
                0,
                "Delete"
        );
        int id = service.create(param);

        service.delete(id);
        assertNull(service.get(id));
        assertFalse(repository.contains(id));
    }
}
