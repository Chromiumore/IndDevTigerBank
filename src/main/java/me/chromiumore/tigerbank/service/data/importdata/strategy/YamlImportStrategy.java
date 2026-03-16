package me.chromiumore.tigerbank.service.data.importdata.strategy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import me.chromiumore.tigerbank.domain.BankAccount;
import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.Category;
import me.chromiumore.tigerbank.domain.Operation;
import me.chromiumore.tigerbank.repository.AccountRepository;
import me.chromiumore.tigerbank.repository.CategoryRepository;
import me.chromiumore.tigerbank.repository.OperationsRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class YamlImportStrategy extends ImportStrategy {
    @Override
    protected Map<Integer, BaseEntity> convertData() {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.registerModule(new JSR310Module());


        Map<Integer, ? extends BaseEntity> data = new HashMap<>();

        try {
            Path path = Paths.get("./target/" +  fileName + getFileExtension());

            String content = Files.readString(path);
            if (repository instanceof AccountRepository) {
                data = objectMapper.readValue(
                        content,
                        new TypeReference<Map<Integer, BankAccount>>() {}
                );
            }
            if (repository instanceof CategoryRepository) {
                data = objectMapper.readValue(
                        content,
                        new TypeReference<Map<Integer, Category>>() {}
                );
            }
            if (repository instanceof OperationsRepository) {
                data = objectMapper.readValue(
                        content,
                        new TypeReference<Map<Integer, Operation>>() {}
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (Map<Integer, BaseEntity>) data;
    }

    @Override
    protected String getFileExtension() {
        return ".yaml";
    }
}
