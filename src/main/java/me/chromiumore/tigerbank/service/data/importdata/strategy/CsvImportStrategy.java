package me.chromiumore.tigerbank.service.data.importdata.strategy;

import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.OperationType;
import me.chromiumore.tigerbank.domain.param.BankAccountParam;
import me.chromiumore.tigerbank.domain.param.CategoryParam;
import me.chromiumore.tigerbank.domain.param.OperationParam;
import me.chromiumore.tigerbank.factory.BankAccountFactory;
import me.chromiumore.tigerbank.factory.CategoryFactory;
import me.chromiumore.tigerbank.factory.OperationFactory;
import me.chromiumore.tigerbank.repository.AccountRepository;
import me.chromiumore.tigerbank.repository.CategoryRepository;
import me.chromiumore.tigerbank.repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CsvImportStrategy extends ImportStrategy {
    @Autowired
    BankAccountFactory accountFactory;
    @Autowired
    CategoryFactory categoryFactory;
    @Autowired
    OperationFactory operationFactory;

    @Override
    protected Map<Integer, BaseEntity> convertData() {
        Map<Integer, BaseEntity> data = new HashMap<>();

        try {
            Path path = Paths.get("./target/" +  fileName + getFileExtension());

            List<String> content = Files.readAllLines(path);
            if (content.isEmpty()) {
                return data;
            }

            for (int i = 1; i < content.size(); i++) {
                String[] rawValues = content.get(i).split(",");

                if (repository instanceof AccountRepository) {
                    data.put(
                            Integer.parseInt(rawValues[0]),
                            accountFactory.createEntity(
                                    new BankAccountParam(
                                            rawValues[1],
                                            Double.parseDouble(rawValues[2])
                                    )
                            )
                    );
                }
                if (repository instanceof CategoryRepository) {
                    data.put(
                            Integer.parseInt(rawValues[0]),
                            categoryFactory.createEntity(
                                    new CategoryParam(
                                            OperationType.valueOf(rawValues[1]),
                                            rawValues[2]
                                    )
                            )
                    );
                }
                if (repository instanceof OperationsRepository) {
                    data.put(
                            Integer.parseInt(rawValues[0]),
                            operationFactory.createEntityWithDate(
                                    new OperationParam(
                                            OperationType.valueOf(rawValues[1]),
                                            Integer.parseInt(rawValues[2]),
                                            Double.parseDouble(rawValues[3]),
                                            Integer.parseInt(rawValues[6]),
                                            rawValues[5]
                                    ),
                                    LocalDate.parse(rawValues[4])
                            )
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected String getFileExtension() {
        return ".csv";
    }
}
