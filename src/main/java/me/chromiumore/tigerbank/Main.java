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
import me.chromiumore.tigerbank.service.entity.BankAccountService;
import me.chromiumore.tigerbank.service.entity.CategoryService;
import me.chromiumore.tigerbank.service.entity.OperationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.time.LocalDate;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        BankAccountService accountService = context.getBean(BankAccountService.class);
        CategoryService categoryService = context.getBean(CategoryService.class);
        OperationService operationService = context.getBean(OperationService.class);

        AnalyticsService analyticsService = context.getBean(AnalyticsService.class);
        ExportService exportService = context.getBean(ExportService.class);

        BankAccount account1 =  (BankAccount) accountService.create(
                new BankAccountParam(
                        "Основной счет",
                        50000
                ));
        BankAccount account2 = (BankAccount) accountService.create(
                new BankAccountParam(
                        "Сберегательный",
                        100000
                ));
        BankAccount account3 = (BankAccount) accountService.create(
                new BankAccountParam(
                        "Наличные",
                        15000
                ));


        Category categoryCafe = (Category) categoryService.create(
                new CategoryParam(
                        OperationType.EXPENSE,
                        "Кафе"
                ));
        Category categoryHealth = (Category) categoryService.create(
                new CategoryParam(
                        OperationType.EXPENSE,
                        "Здоровье"
                ));
        Category categoryTransport = (Category) categoryService.create(
                new CategoryParam(
                        OperationType.EXPENSE,
                        "Транспорт"
                ));
        Category categoryProducts = (Category) categoryService.create(
                new CategoryParam(
                        OperationType.EXPENSE,
                        "Продукты"
                ));
        Category categorySalary = (Category) categoryService.create(
                new CategoryParam(
                        OperationType.INCOME,
                        "Зарплата"
                ));
        Category categoryCashback = (Category) categoryService.create(
                new CategoryParam(
                        OperationType.INCOME,
                        "Кэшбэк"
                ));
        Category categoryGift = (Category) categoryService.create(
                new CategoryParam(
                        OperationType.INCOME,
                        "Подарки"
                ));


        Operation operation1 = (Operation) operationService.create(
                new OperationParam(
                        OperationType.INCOME,
                        account1,
                        75000,
                        categorySalary,
                        "Зарплата за январь"
        ));
        account1.deposit(75000);

        Operation operation2 = (Operation) operationService.create(
                new OperationParam(
                        OperationType.EXPENSE,
                        account1,
                        1500,
                        categoryCafe,
                        "Обед в кафе"
        ));
        account1.withdraw(1500);

        Operation operation3 = (Operation) operationService.create(
                new OperationParam(
                        OperationType.EXPENSE,
                        account1,
                        3500,
                        categoryProducts,
                        "Продукты на неделю"
        ));
        account1.withdraw(3500);

        Operation operation4 = (Operation) operationService.create(
                new OperationParam(
                        OperationType.INCOME,
                        account2,
                        5000,
                        categoryCashback,
                        "Кэшбэк за покупки"
        ));
        account2.deposit(5000);

        Operation operation5 = (Operation) operationService.create(
                new OperationParam(
                        OperationType.EXPENSE,
                        account3,
                        1000,
                        categoryTransport,
                        "Проездной на месяц"
        ));
        account3.withdraw(1000);


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