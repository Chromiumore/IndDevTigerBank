package me.chromiumore.tigerbank.service.data.importdata.strategy;

import lombok.Setter;
import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.repository.BaseRepository;

import java.util.Map;

@Setter
public abstract class ImportStrategy {
    protected String fileName;
    protected BaseRepository repository;

    protected abstract Map<Integer, BaseEntity> convertData();

    public void saveFromFile() {
        Map<Integer, BaseEntity> data = convertData();
        repository.setStorage(data);
    }
}
