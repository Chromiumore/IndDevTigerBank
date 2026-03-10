package me.chromiumore.tigerbank.services.output;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.stereotype.Component;

@Component
public class YamlOutputStrategy extends OutputStrategy {
    public YamlOutputStrategy() {
        super(".yaml");
    }

    @Override
    protected String dataToFormat() {
        if (data.values().isEmpty()) {
            return "";
        }

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.registerModule(new JSR310Module());
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }
}
