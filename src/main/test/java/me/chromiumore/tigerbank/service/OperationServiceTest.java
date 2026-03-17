package me.chromiumore.tigerbank.service;

import me.chromiumore.tigerbank.domain.BankAccount;
import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.Operation;
import me.chromiumore.tigerbank.domain.OperationType;
import me.chromiumore.tigerbank.domain.param.BankAccountParam;
import me.chromiumore.tigerbank.domain.param.CategoryParam;
import me.chromiumore.tigerbank.domain.param.OperationNoTypeParam;
import me.chromiumore.tigerbank.domain.param.OperationParam;
import me.chromiumore.tigerbank.factory.OperationFactory;
import me.chromiumore.tigerbank.repository.AccountRepository;
import me.chromiumore.tigerbank.repository.CategoryRepository;
import me.chromiumore.tigerbank.repository.OperationsRepository;
import me.chromiumore.tigerbank.service.entity.BankAccountService;
import me.chromiumore.tigerbank.service.entity.CategoryService;
import me.chromiumore.tigerbank.service.entity.OperationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Работа сервиса операций")
public class OperationServiceTest {
    @Autowired
    private OperationService operationService;
    @Autowired
    private BankAccountService accountService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OperationsRepository operationsRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OperationFactory factory;
    private static int accountId;
    private static int incomeCategoryId;
    private static int expenseCategoryId;

    @BeforeEach
    public void cleanup() {
        BankAccountParam bankAccountParam = new BankAccountParam(
                "AccForOperations",
                1200
        );

        accountId = accountService.create(bankAccountParam);

        CategoryParam incomeCategoryParam = new CategoryParam(
                OperationType.INCOME,
                "CatIncome"
        );
        incomeCategoryId = categoryService.create(incomeCategoryParam);

        CategoryParam expenseCategoryParam = new CategoryParam(
                OperationType.EXPENSE,
                "CatExpense"
        );
        expenseCategoryId = categoryService.create(expenseCategoryParam);
    }

    @Test
    @DisplayName("Пополнение и списание")
    public void depositAndWithdraw() {
        double toDeposit = 150.25;
        double toWithdraw = 100;

        BankAccount account = (BankAccount) accountService.get(accountId);
        double balance = account.getBalance();

        OperationNoTypeParam operationParam = new OperationNoTypeParam(
                accountId,
                toDeposit,
                incomeCategoryId,
                "IncomeOp"
        );
        Operation operation = (Operation) operationService.get(operationService.deposit(operationParam));

        assertEquals(balance + toDeposit, account.getBalance());
        assertEquals(OperationType.INCOME, operation.getType());
        assertEquals(operationParam.getBankAccountId(), operation.getBankAccountId());
        assertEquals(operationParam.getAmount(), operation.getAmount());
        assertEquals(operationParam.getCategoryId(), operation.getCategoryId());
        assertEquals(operationParam.getDescription(), operation.getDescription());

        operationParam = new OperationNoTypeParam(
                accountId,
                toWithdraw,
                expenseCategoryId,
                "ExpenseOp"
        );
        operation = (Operation) operationService.get(operationService.withdraw(operationParam));

        assertEquals(balance + toDeposit - toWithdraw, account.getBalance());
        assertEquals(OperationType.EXPENSE, operation.getType());
        assertEquals(operationParam.getBankAccountId(), operation.getBankAccountId());
        assertEquals(operationParam.getAmount(), operation.getAmount());
        assertEquals(operationParam.getCategoryId(), operation.getCategoryId());
        assertEquals(operationParam.getDescription(), operation.getDescription());
    }

    @Test
    @DisplayName("Редактирование операции через сервис")
    public void updateOperationTest() {
        OperationNoTypeParam param = new OperationNoTypeParam(
                accountId,
                800,
                incomeCategoryId,
                "Old"
        );
        int id = operationService.deposit(param);
        assertNotNull(operationsRepository.get(id));

        Operation operation = (Operation) factory.createEntity(
                new OperationParam(
                        OperationType.EXPENSE,
                        accountId,
                        450,
                        incomeCategoryId,
                        "Update"
                )
        );
        operationService.update(id, operation);

        BaseEntity updatedEntity = operationsRepository.get(id);

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
        OperationNoTypeParam param = new OperationNoTypeParam(
                accountId,
                20.7,
                expenseCategoryId,
                "Get"
        );
        int id = operationService.withdraw(param);

        BaseEntity entity = operationService.get(id);
        assertNotNull(entity);
        assertInstanceOf(Operation.class, entity);

        Operation operation = (Operation) entity;
        assertEquals(OperationType.EXPENSE, operation.getType());
        assertEquals(param.getBankAccountId(), operation.getBankAccountId());
        assertEquals(param.getAmount(), operation.getAmount());
        assertEquals(param.getCategoryId(), operation.getCategoryId());
        assertEquals(param.getDescription(), operation.getDescription());
    }

    @Test
    @DisplayName("Удаление операции через сервис")
    public void deleteOperationTest() {
        OperationNoTypeParam param = new OperationNoTypeParam(
                accountId,
                0.6,
                incomeCategoryId,
                "Delete"
        );
        int id = operationService.deposit(param);

        operationService.delete(id);
        assertNull(operationService.get(id));
        assertFalse(operationsRepository.contains(id));
    }
}
