package me.chromiumore.tigerbank.service.data.importdata.strategy;

import me.chromiumore.tigerbank.domain.BaseEntity;

import java.util.Map;

public class YamlImportStrategy extends ImportStrategy {
    @Override
    protected Map<Integer, BaseEntity> convertData() {
        return Map.of();
    }
}
