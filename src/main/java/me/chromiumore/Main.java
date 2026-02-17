package me.chromiumore;

import me.chromiumore.entities.*;
import me.chromiumore.repositories.AccountRepository;
import me.chromiumore.repositories.CategoryRepository;
import me.chromiumore.repositories.OperationsRepository;
import me.chromiumore.services.AnalyticsService;
import me.chromiumore.services.ExportService;
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

        BankAccount account1 = new BankAccount("Основной счет", 50000);
        BankAccount account2 = new BankAccount("Сберегательный", 100000);
        BankAccount account3 = new BankAccount("Наличные", 15000);

        Category categoryCafe = new Category(OperationType.EXPENSE, "Кафе");
        Category categoryHealth = new Category(OperationType.EXPENSE, "Здоровье");
        Category categoryTransport = new Category(OperationType.EXPENSE, "Транспорт");
        Category categoryProducts = new Category(OperationType.EXPENSE, "Продукты");
        Category categorySalary = new Category(OperationType.INCOME, "Зарплата");
        Category categoryCashback = new Category(OperationType.INCOME, "Кэшбэк");
        Category categoryGift = new Category(OperationType.INCOME, "Подарки");

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

        Operation operation1 = new Operation(
                OperationType.INCOME,
                account1.getId(),
                75000,
                categorySalary.getId(),
                "Зарплата за январь"
        );
        account1.deposit(75000);

        Operation operation2 = new Operation(
                OperationType.EXPENSE,
                account1.getId(),
                1500,
                categoryCafe.getId(),
                "Обед в кафе"
        );
        account1.withdraw(1500);

        Operation operation3 = new Operation(
                OperationType.EXPENSE,
                account1.getId(),
                3500,
                categoryProducts.getId(),
                "Продукты на неделю"
        );
        account1.withdraw(3500);

        Operation operation4 = new Operation(
                OperationType.INCOME,
                account2.getId(),
                5000,
                categoryCashback.getId(),
                "Кэшбэк за покупки"
        );
        account2.deposit(5000);

        Operation operation5 = new Operation(
                OperationType.EXPENSE,
                account3.getId(),
                1000,
                categoryTransport.getId(),
                "Проездной на месяц"
        );
        account3.withdraw(1000);

        operationsRepository.add(operation1);
        operationsRepository.add(operation2);
        operationsRepository.add(operation3);
        operationsRepository.add(operation4);
        operationsRepository.add(operation5);

        AnalyticsService analyticsService = context.getBean(AnalyticsService.class);
        System.out.println(analyticsService.getExpenseByCategory(
                LocalDate.of(2020, 12, 1),
                LocalDate.of(2027, 02, 17)
        ));
        System.out.println(analyticsService.getIncomeExpenseDifference(
                LocalDate.of(2020, 12, 1),
                LocalDate.of(2027, 02, 17)
        ));

        ExportService exportService = context.getBean(ExportService.class);
        try {
            exportService.exportToCsv();
        } catch (IOException e) {
            System.out.println("Ошибка записи данных в формате csv");
        }
    }
}