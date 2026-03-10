package me.chromiumore.tigerbank.services;

import me.chromiumore.tigerbank.repositories.AccountRepository;
import me.chromiumore.tigerbank.repositories.CategoryRepository;
import me.chromiumore.tigerbank.repositories.OperationsRepository;
import me.chromiumore.tigerbank.services.output.CSVOutputStrategy;
import me.chromiumore.tigerbank.services.output.JsonOutputStrategy;
import me.chromiumore.tigerbank.services.output.OutputStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ExportService {

    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;
    private final OperationsRepository operationsRepository;
    private final CSVOutputStrategy csvOutputStrategy;
    private final JsonOutputStrategy jsonOutputStrategy;

    @Autowired
    public ExportService(AccountRepository accountRepository,
                         CategoryRepository categoryRepository,
                         OperationsRepository operationsRepository,
                         CSVOutputStrategy csvOutputStrategy, JsonOutputStrategy jsonOutputStrategy) {
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.operationsRepository = operationsRepository;
        this.csvOutputStrategy = csvOutputStrategy;
        this.jsonOutputStrategy = jsonOutputStrategy;
    }

    public void exportToCsv() throws IOException {
        exportData(csvOutputStrategy);
    }

    public void exportToJson() throws IOException {
        exportData(jsonOutputStrategy);
    }

    private void exportData(OutputStrategy strategy) throws IOException {
        strategy.setData(accountRepository.getAll());
        strategy.setFileName("accounts_export");
        strategy.writeFile();

        strategy.setData(categoryRepository.getAll());
        strategy.setFileName("categories_export");
        strategy.writeFile();

        strategy.setData(operationsRepository.getAll());
        strategy.setFileName("operations_export");
        strategy.writeFile();
    }
}