package me.chromiumore.tigerbank.service.data;

import me.chromiumore.tigerbank.domain.Category;
import me.chromiumore.tigerbank.domain.Operation;
import me.chromiumore.tigerbank.domain.OperationType;
import me.chromiumore.tigerbank.domain.param.BankAccountParam;
import me.chromiumore.tigerbank.domain.param.CategoryParam;
import me.chromiumore.tigerbank.domain.param.OperationParam;
import me.chromiumore.tigerbank.repository.AccountRepository;
import me.chromiumore.tigerbank.repository.CategoryRepository;
import me.chromiumore.tigerbank.repository.OperationsRepository;
import me.chromiumore.tigerbank.service.AnalyticsService;
import me.chromiumore.tigerbank.service.data.exportdata.ExportService;
import me.chromiumore.tigerbank.service.data.importdata.ImportService;
import me.chromiumore.tigerbank.service.entity.BankAccountService;
import me.chromiumore.tigerbank.service.entity.CategoryService;
import me.chromiumore.tigerbank.service.entity.OperationService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

@SpringBootTest
@DisplayName("Экспорт и импорт данных")
public class ImportExportTest {
    @Autowired
    private BankAccountService accountService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OperationService operationService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OperationsRepository operationsRepository;
    @Autowired
    private ExportService exportService;
    @Autowired
    private ImportService importService;
    @Autowired
    private AnalyticsService analyticsService;
    private static String accName = "test_accounts";
    private static String catName = "test_categories";
    private static String opName = "test_operations";

    public void setup() {
        exportService.setAccountsExportName(accName);
        exportService.setCategoriesExportName(catName);
        exportService.setOperationsExportName(opName);
        importService.setAccountsExportName(accName);
        importService.setCategoriesExportName(catName);
        importService.setOperationsExportName(opName);
    }

    @BeforeAll
    public static void cleanup() {
        String[] names = {accName, catName, opName};
        String[] extensions = {".yaml", ".csv", ".json"};
        for (String n : names) {
            for (String e : extensions) {
                try {
                    Files.deleteIfExists(Paths.get("./target/" + n + e));
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    @Test
    @DisplayName("Импорт и экспорт")
    public void ImportThenExportDataTest() {
        setup();

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
        int categoryCafeId = categoryService.create(
                new CategoryParam(
                        OperationType.EXPENSE,
                        "Кафе"
                ));
        String categoryCafeName = ((Category) categoryService.get(categoryCafeId)).getName();
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


        int operationId1 = operationService.deposit(
                new OperationParam(
                        OperationType.INCOME,
                        accountId1,
                        75000,
                        categorySalaryId,
                        "Зарплата за январь"
                ));

        int operationId2 = operationService.withdraw(
                new OperationParam(
                        OperationType.EXPENSE,
                        accountId1,
                        1500,
                        categoryCafeId,
                        "Обед в кафе"
                ));

        int operationId3 = operationService.withdraw(
                new OperationParam(
                        OperationType.EXPENSE,
                        accountId1,
                        3500,
                        categoryProductsId,
                        "Продукты на неделю"
                ));
        OperationType operation3Type = ((Operation) operationService.get(operationId3)).getType();
        double operation3Amount = ((Operation) operationService.get(operationId3)).getAmount();
        LocalDate operation3Date = ((Operation) operationService.get(operationId3)).getDate();

        int operationId4 = operationService.deposit(
                new OperationParam(
                        OperationType.INCOME,
                        accountId2,
                        5000,
                        categoryCashbackId,
                        "Кэшбэк за покупки"
                ));

        int operationId5 = operationService.withdraw(
                new OperationParam(
                        OperationType.EXPENSE,
                        accountId3,
                        1000,
                        categoryTransportId,
                        "Проездной на месяц"
                ));


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

        accountRepository.clear();
        categoryRepository.clear();
        operationsRepository.clear();

        importService.importFromCsv();
        assertNotNull(categoryService.get(categoryCafeId));
        assertEquals(categoryCafeName, ((Category)categoryService.get(categoryCafeId)).getName());
        assertEquals(operation3Amount, ((Operation) operationService.get(2)).getAmount());
        assertEquals(operation3Date, ((Operation) operationService.get(2)).getDate());
        assertEquals(operation3Type, ((Operation) operationService.get(2)).getType());
    }
}
