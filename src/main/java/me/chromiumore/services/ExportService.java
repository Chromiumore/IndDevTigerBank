package me.chromiumore.services;

import me.chromiumore.repositories.AccountRepository;
import me.chromiumore.repositories.CategoryRepository;
import me.chromiumore.repositories.OperationsRepository;
import me.chromiumore.services.output.CSVOutputStrategy;
import me.chromiumore.services.output.OutputStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ExportService {

    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;
    private final OperationsRepository operationsRepository;
    private final CSVOutputStrategy csvOutputStrategy;

    @Autowired
    public ExportService(AccountRepository accountRepository,
                         CategoryRepository categoryRepository,
                         OperationsRepository operationsRepository,
                         CSVOutputStrategy csvOutputStrategy) {
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.operationsRepository = operationsRepository;
        this.csvOutputStrategy = csvOutputStrategy;
    }

    public void exportToCsv() throws IOException {
        exportData(csvOutputStrategy);
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