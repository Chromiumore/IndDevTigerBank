package me.chromiumore.tigerbank.services.output;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import me.chromiumore.tigerbank.entities.BaseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

        List<BaseEntity> entities = new ArrayList<>(data.values());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR310Module());
        try {
            return objectMapper.writeValueAsString(entities);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }
}
