package me.chromiumore.tigerbank.service.data.importdata;

import lombok.Getter;
import lombok.Setter;
import me.chromiumore.tigerbank.aop.LogExecutionTime;
import me.chromiumore.tigerbank.repository.AccountRepository;
import me.chromiumore.tigerbank.repository.CategoryRepository;
import me.chromiumore.tigerbank.repository.OperationsRepository;
import me.chromiumore.tigerbank.service.data.DataService;
import me.chromiumore.tigerbank.service.data.importdata.strategy.CsvImportStrategy;
import me.chromiumore.tigerbank.service.data.importdata.strategy.ImportStrategy;
import me.chromiumore.tigerbank.service.data.importdata.strategy.JsonImportStrategy;
import me.chromiumore.tigerbank.service.data.importdata.strategy.YamlImportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImportService extends DataService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    OperationsRepository operationsRepository;
    @Autowired
    JsonImportStrategy jsonStrategy;
    @Autowired
    YamlImportStrategy yamlStrategy;
    @Autowired
    CsvImportStrategy csvStrategy;

    @LogExecutionTime
    public void importFromJson() {
        importData(jsonStrategy);
    }

    @LogExecutionTime
    public void importFromYaml() {
        importData(yamlStrategy);
    }

    @LogExecutionTime
    public void importFromCsv() {
        importData(csvStrategy);
    }

    private void importData(ImportStrategy strategy) {
        strategy.setFileName(accountsExportName);
        strategy.setRepository(accountRepository);
        strategy.saveFromFile();

        strategy.setFileName(categoriesExportName);
        strategy.setRepository(categoryRepository);
        strategy.saveFromFile();

        strategy.setFileName(operationsExportName);
        strategy.setRepository(operationsRepository);
        strategy.saveFromFile();
    }
}
