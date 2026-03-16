package me.chromiumore.tigerbank;

import me.chromiumore.tigerbank.domain.*;
import me.chromiumore.tigerbank.domain.param.BankAccountParam;
import me.chromiumore.tigerbank.domain.param.CategoryParam;
import me.chromiumore.tigerbank.domain.param.OperationParam;
import me.chromiumore.tigerbank.service.AnalyticsService;
import me.chromiumore.tigerbank.service.data.exportdata.ExportService;
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

        int accountId1 =   accountService.create(
                new BankAccountParam(
                        "Основной счет",
                        50000
                ));
        int accountId2 = accountService.create(
                new BankAccountParam(
                        "Сберегательный",
                        100000
                ));
        int accountId3 = accountService.create(
                new BankAccountParam(
                        "Наличные",
                        15000
                ));


        int categoryCafeId = categoryService.create(
                new CategoryParam(
                        OperationType.EXPENSE,
                        "Кафе"
                ));
        int categoryHealthId = categoryService.create(
                new CategoryParam(
                        OperationType.EXPENSE,
                        "Здоровье"
                ));
        int categoryTransportId = categoryService.create(
                new CategoryParam(
                        OperationType.EXPENSE,
                        "Транспорт"
                ));
        int categoryProductsId = categoryService.create(
                new CategoryParam(
                        OperationType.EXPENSE,
                        "Продукты"
                ));
        int categorySalaryId = categoryService.create(
                new CategoryParam(
                        OperationType.INCOME,
                        "Зарплата"
                ));
        int categoryCashbackId = categoryService.create(
                new CategoryParam(
                        OperationType.INCOME,
                        "Кэшбэк"
                ));
        int categoryGiftId = categoryService.create(
                new CategoryParam(
                        OperationType.INCOME,
                        "Подарки"
                ));


        int operationId1 = operationService.create(
                new OperationParam(
                        OperationType.INCOME,
                        accountId1,
                        75000,
                        categorySalaryId,
                        "Зарплата за январь"
        ));
        accountService.deposit(accountId1, 75000);

        int operationId2 = operationService.create(
                new OperationParam(
                        OperationType.EXPENSE,
                        accountId1,
                        1500,
                        categoryCafeId,
                        "Обед в кафе"
        ));
        accountService.withdraw(accountId1, 1500);

        int operationId3 = operationService.create(
                new OperationParam(
                        OperationType.EXPENSE,
                        accountId1,
                        3500,
                        categoryProductsId,
                        "Продукты на неделю"
        ));
        accountService.withdraw(accountId1, 3500);

        int operationId4 = operationService.create(
                new OperationParam(
                        OperationType.INCOME,
                        accountId2,
                        5000,
                        categoryCashbackId,
                        "Кэшбэк за покупки"
        ));
        accountService.deposit(accountId2, 5000);

        int operationId5 = operationService.create(
                new OperationParam(
                        OperationType.EXPENSE,
                        accountId3,
                        1000,
                        categoryTransportId,
                        "Проездной на месяц"
        ));
        accountService.withdraw(accountId3, 1000);


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