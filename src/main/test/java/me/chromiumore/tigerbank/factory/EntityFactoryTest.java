package me.chromiumore.tigerbank.factory;

import me.chromiumore.tigerbank.domain.*;
import me.chromiumore.tigerbank.domain.param.BankAccountParam;
import me.chromiumore.tigerbank.domain.param.CategoryParam;
import me.chromiumore.tigerbank.domain.param.OperationParam;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Создание объектов с помощью фабрик")
public class EntityFactoryTest {
    private static BankAccountFactory accountFactory;
    private static CategoryFactory categoryFactory;
    private static OperationFactory operationFactory;
    private static OperationParam operationDefaultParam;

    @BeforeAll
    public static void setup() {
        accountFactory = new BankAccountFactory();
        categoryFactory = new CategoryFactory();
        operationFactory = new OperationFactory();
        operationDefaultParam = new OperationParam(
                OperationType.INCOME,
                3,
                400.2,
                1,
                "Test Desc"
        );
    }

    @Test
    @DisplayName("Создание счёта")
    public void createBankAccount() {
        BankAccountParam accountParam = new BankAccountParam(
                "Test-123",
                500.5
        );

        BaseEntity entity = accountFactory.createEntity(accountParam);

        assertInstanceOf(BankAccount.class, entity);

        BankAccount account = (BankAccount) entity;
        assertEquals(accountParam.getName(), account.getName());
        assertEquals(accountParam.getBalance(), account.getBalance());
    }

    @Test
    @DisplayName("Создание категории")
    public void createCategory() {
        CategoryParam categoryParam = new CategoryParam(
                OperationType.EXPENSE,
                "Test-cat"
        );

        BaseEntity entity = categoryFactory.createEntity(categoryParam);

        assertInstanceOf(Category.class, entity);

        Category category = (Category) entity;
        assertEquals(categoryParam.getName(), category.getName());
        assertEquals(categoryParam.getType(), category.getType());
    }

    @Test
    @DisplayName("Создание операции без дополнительных параметров")
    public void createOperationWithDefaultParam() {
        BaseEntity entity = operationFactory.createEntity(operationDefaultParam);

        assertInstanceOf(Operation.class, entity);

        Operation operation = (Operation) entity;
        assertEquals(operationDefaultParam.getType(), operation.getType());
        assertEquals(operationDefaultParam.getBankAccountId(), operation.getBankAccountId());
        assertEquals(operationDefaultParam.getAmount(), operation.getAmount());
        assertEquals(operationDefaultParam.getCategoryId(), operation.getCategoryId());
        assertEquals(operationDefaultParam.getDescription(), operation.getDescription());
    }

    @Test
    @DisplayName("Создание операции с параметром датой")
    public void createOperationWithDate() {
        LocalDate date = LocalDate.of(2004, 1, 24);

        BaseEntity entity = operationFactory.createEntityWithDate(operationDefaultParam, date);

        assertInstanceOf(Operation.class, entity);

        Operation operation = (Operation) entity;
        assertEquals(operationDefaultParam.getType(), operation.getType());
        assertEquals(operationDefaultParam.getBankAccountId(), operation.getBankAccountId());
        assertEquals(operationDefaultParam.getAmount(), operation.getAmount());
        assertEquals(operationDefaultParam.getCategoryId(), operation.getCategoryId());
        assertEquals(operationDefaultParam.getDescription(), operation.getDescription());
        assertEquals(date, operation.getDate());
    }
}
