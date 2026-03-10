package me.chromiumore.tigerbank.services.output;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.stereotype.Component;

@Component
public class JsonOutputStrategy extends OutputStrategy{
    public JsonOutputStrategy() {
        super(".json");
    }

    @Override
    protected String dataToFormat() {
        if (data.values().isEmpty()) {
            return "";
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR310Module());
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }
}
