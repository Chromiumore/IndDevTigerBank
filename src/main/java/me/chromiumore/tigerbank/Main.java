package me.chromiumore.tigerbank;

import me.chromiumore.tigerbank.domain.*;
import me.chromiumore.tigerbank.domain.param.BankAccountParam;
import me.chromiumore.tigerbank.domain.param.CategoryParam;
import me.chromiumore.tigerbank.domain.param.OperationParam;
import me.chromiumore.tigerbank.factory.BankAccountFactory;
import me.chromiumore.tigerbank.factory.CategoryFactory;
import me.chromiumore.tigerbank.factory.OperationFactory;
import me.chromiumore.tigerbank.repository.AccountRepository;
import me.chromiumore.tigerbank.repository.CategoryRepository;
import me.chromiumore.tigerbank.repository.OperationsRepository;
import me.chromiumore.tigerbank.service.AnalyticsService;
import me.chromiumore.tigerbank.service.ExportService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.time.LocalDate;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        AccountRepository accountRepository = context.getBean(AccountRepository.class);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);
        OperationsRepository operationsRepository = context.getBean(OperationsRepository.class);

        BankAccountFactory accountFactory = context.getBean(BankAccountFactory.class);
        CategoryFactory categoryFactory = context.getBean(CategoryFactory.class);
        OperationFactory operationFactory = context.getBean(OperationFactory.class);

        AnalyticsService analyticsService = context.getBean(AnalyticsService.class);
        ExportService exportService = context.getBean(ExportService.class);

        BankAccount account1 = (BankAccount) accountFactory.createEntity(
                new BankAccountParam(
                        "Основной счет",
                        50000
                ));
        BankAccount account2 = (BankAccount) accountFactory.createEntity(
                new BankAccountParam(
                        "Сберегательный",
                        100000
                ));
        BankAccount account3 = (BankAccount) accountFactory.createEntity(
                new BankAccountParam(
                        "Наличные",
                        15000
                ));

        Category categoryCafe = (Category) categoryFactory.createEntity(
                new CategoryParam(
                        OperationType.EXPENSE,
                        "Кафе"
                ));
        Category categoryHealth = (Category) categoryFactory.createEntity(
                new CategoryParam(
                        OperationType.EXPENSE,
                        "Здоровье"
                ));
        Category categoryTransport = (Category) categoryFactory.createEntity(
                new CategoryParam(
                        OperationType.EXPENSE,
                        "Транспорт"
                ));
        Category categoryProducts = (Category) categoryFactory.createEntity(
                new CategoryParam(
                        OperationType.EXPENSE,
                        "Продукты"
                ));
        Category categorySalary = (Category) categoryFactory.createEntity(
                new CategoryParam(
                        OperationType.INCOME,
                        "Зарплата"
                ));
        Category categoryCashback = (Category) categoryFactory.createEntity(
                new CategoryParam(
                        OperationType.INCOME,
                        "Кэшбэк"
                ));
        Category categoryGift = (Category) categoryFactory.createEntity(
                new CategoryParam(
                        OperationType.INCOME,
                        "Подарки"
                ));

        accountRepository.add(account1);
        accountRepository.add(account2);
        accountRepository.add(account3);

        categoryRepository.add(categoryCafe);
        categoryRepository.add(categoryHealth);
        categoryRepository.add(categoryTransport);
        categoryRepository.add(categoryProducts);
        categoryRepository.add(categorySalary);
        categoryRepository.add(categoryCashback);
        categoryRepository.add(categoryGift);

        Operation operation1 = (Operation) operationFactory.createEntity(
                new OperationParam(
                        OperationType.INCOME,
                        account1,
                        75000,
                        categorySalary,
                        "Зарплата за январь"
        ));
        account1.deposit(75000);

        Operation operation2 = (Operation) operationFactory.createEntity(
                new OperationParam(
                        OperationType.EXPENSE,
                        account1,
                        1500,
                        categoryCafe,
                        "Обед в кафе"
        ));
        account1.withdraw(1500);

        Operation operation3 = (Operation) operationFactory.createEntity(
                new OperationParam(
                        OperationType.EXPENSE,
                        account1,
                        3500,
                        categoryProducts,
                        "Продукты на неделю"
        ));
        account1.withdraw(3500);

        Operation operation4 = (Operation) operationFactory.createEntity(
                new OperationParam(
                        OperationType.INCOME,
                        account2,
                        5000,
                        categoryCashback,
                        "Кэшбэк за покупки"
        ));
        account2.deposit(5000);

        Operation operation5 = (Operation) operationFactory.createEntity(
                new OperationParam(
                        OperationType.EXPENSE,
                        account3,
                        1000,
                        categoryTransport,
                        "Проездной на месяц"
        ));
        account3.withdraw(1000);

        operationsRepository.add(operation1);
        operationsRepository.add(operation2);
        operationsRepository.add(operation3);
        operationsRepository.add(operation4);
        operationsRepository.add(operation5);

        System.out.println(analyticsService.getExpenseByCategory(
                LocalDate.of(2020, 12, 1),
                LocalDate.of(2027, 2, 17)
        ));
        System.out.println(analyticsService.getIncomeExpenseDifference(
                LocalDate.of(2020, 12, 1),
                LocalDate.of(2027, 2, 17)
        ));


        try {
            exportService.exportToCsv();
        } catch (IOException e) {
            System.out.println("Ошибка записи данных в формате csv");
        }

        try {
            exportService.exportToJson();
        } catch (IOException e) {
            System.out.println("Ошибка записи данных в формате json");
        }

        try {
            exportService.exportToYaml();
        } catch (IOException e) {
            System.out.println("Ошибка записи данных в формате yaml");
        }
    }
}