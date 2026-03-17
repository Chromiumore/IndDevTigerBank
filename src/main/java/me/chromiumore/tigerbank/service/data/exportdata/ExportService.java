package me.chromiumore.tigerbank.service.data.exportdata;

import me.chromiumore.tigerbank.aop.LogExecutionTime;
import me.chromiumore.tigerbank.repository.AccountRepository;
import me.chromiumore.tigerbank.repository.CategoryRepository;
import me.chromiumore.tigerbank.repository.OperationsRepository;
import me.chromiumore.tigerbank.service.data.exportdata.output.CSVOutputStrategy;
import me.chromiumore.tigerbank.service.data.exportdata.output.JsonOutputStrategy;
import me.chromiumore.tigerbank.service.data.exportdata.output.OutputStrategy;
import me.chromiumore.tigerbank.service.data.exportdata.output.YamlOutputStrategy;
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
    private final YamlOutputStrategy yamlOutputStrategy;

    @Autowired
    public ExportService(AccountRepository accountRepository,
                         CategoryRepository categoryRepository,
                         OperationsRepository operationsRepository,
                         CSVOutputStrategy csvOutputStrategy,
                         JsonOutputStrategy jsonOutputStrategy,
                         YamlOutputStrategy yamlOutputStrategy) {
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.operationsRepository = operationsRepository;
        this.csvOutputStrategy = csvOutputStrategy;
        this.jsonOutputStrategy = jsonOutputStrategy;
        this.yamlOutputStrategy = yamlOutputStrategy;
    }

    public void exportToCsv() throws IOException {
        exportData(csvOutputStrategy);
    }

    public void exportToJson() throws IOException {
        exportData(jsonOutputStrategy);
    }

    public void exportToYaml() throws IOException {
        exportData(yamlOutputStrategy);
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