package me.chromiumore.tigerbank.service.data.importdata;

import me.chromiumore.tigerbank.repository.AccountRepository;
import me.chromiumore.tigerbank.repository.CategoryRepository;
import me.chromiumore.tigerbank.repository.OperationsRepository;
import me.chromiumore.tigerbank.service.data.importdata.strategy.ImportStrategy;
import me.chromiumore.tigerbank.service.data.importdata.strategy.JsonImportStrategy;
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

    public void importFromJson() {
        importData(jsonStrategy);
    }

    private void importData(ImportStrategy strategy) {
        strategy.setFileName("accounts_export.json");
        strategy.setRepository(accountRepository);
        strategy.saveFromFile();

        strategy.setFileName("categories_export.json");
        strategy.setRepository(categoryRepository);
        strategy.saveFromFile();

        strategy.setFileName("operations_export.json");
        strategy.setRepository(operationsRepository);
        strategy.saveFromFile();
    }
}
