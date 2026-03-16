package me.chromiumore.tigerbank.service.data.importdata;

import me.chromiumore.tigerbank.repository.AccountRepository;
import me.chromiumore.tigerbank.repository.CategoryRepository;
import me.chromiumore.tigerbank.repository.OperationsRepository;
import me.chromiumore.tigerbank.service.data.importdata.strategy.CsvImportStrategy;
import me.chromiumore.tigerbank.service.data.importdata.strategy.ImportStrategy;
import me.chromiumore.tigerbank.service.data.importdata.strategy.JsonImportStrategy;
import me.chromiumore.tigerbank.service.data.importdata.strategy.YamlImportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportService {
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

    public void importFromJson() {
        importData(jsonStrategy);
    }

    public void importFromYaml() {
        importData(yamlStrategy);
    }

    public void importFromCsv() {
        importData(csvStrategy);
    }

    private void importData(ImportStrategy strategy) {
        strategy.setFileName("accounts_export");
        strategy.setRepository(accountRepository);
        strategy.saveFromFile();

        strategy.setFileName("categories_export");
        strategy.setRepository(categoryRepository);
        strategy.saveFromFile();

        strategy.setFileName("operations_export");
        strategy.setRepository(operationsRepository);
        strategy.saveFromFile();
    }
}
