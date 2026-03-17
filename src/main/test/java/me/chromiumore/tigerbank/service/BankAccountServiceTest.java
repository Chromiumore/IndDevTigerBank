package me.chromiumore.tigerbank.service;

import me.chromiumore.tigerbank.domain.BankAccount;
import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.param.BankAccountParam;
import me.chromiumore.tigerbank.repository.AccountRepository;
import me.chromiumore.tigerbank.service.entity.BankAccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Работа сервиса банковских счетов")
public class BankAccountServiceTest {
    @Autowired
    private BankAccountService service;
    @Autowired
    private AccountRepository repository;

    @Test
    @DisplayName("Добавление счёта через сервис")
    public void addAccountTest() {
        BankAccountParam param = new BankAccountParam(
                "CreateAcc",
                400
        );
        int id = service.create(param);

        BaseEntity entity = repository.get(id);
        assertNotNull(entity);
        assertInstanceOf(BankAccount.class, entity);

        BankAccount account = (BankAccount) entity;
        assertEquals(param.getName(), account.getName());
        assertEquals(param.getBalance(), account.getBalance());
    }

    @Test
    @DisplayName("Редактирование счёта через сервис")
    public void updateAccountTest() {
        BankAccountParam param = new BankAccountParam(
                "OldAcc",
                123.23
        );
        int id = service.create(param);
        assertNotNull(repository.get(id));

        BankAccount bankAccount = new BankAccount("UpdateAcc", 321.222);
        service.update(id, bankAccount);

        BaseEntity updatedEntity = repository.get(id);

        assertInstanceOf(BankAccount.class, updatedEntity);
        BankAccount updatedAccount = (BankAccount) updatedEntity;

        assertEquals(bankAccount.getName(), updatedAccount.getName());
        assertEquals(bankAccount.getBalance(), updatedAccount.getBalance());
    }

    @Test
    @DisplayName("Получение счёта через сервис")
    public void getAccountTest() {
        BankAccountParam param = new BankAccountParam(
                "GetAcc",
                0
        );
        int id = service.create(param);

        BaseEntity entity = service.get(id);
        assertNotNull(entity);
        assertInstanceOf(BankAccount.class, entity);

        BankAccount account = (BankAccount) entity;
        assertEquals(param.getName(), account.getName());
        assertEquals(param.getBalance(), account.getBalance());
    }

    @Test
    @DisplayName("Удаление счёта через сервис")
    public void deleteAccountTest() {
        BankAccountParam param = new BankAccountParam(
                "DeleteAcc",
                1400
        );
        int id = service.create(param);

        service.delete(id);
        assertNull(service.get(id));
        assertFalse(repository.contains(id));
    }
}
