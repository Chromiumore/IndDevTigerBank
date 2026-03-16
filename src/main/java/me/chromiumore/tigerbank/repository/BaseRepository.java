package me.chromiumore.tigerbank.repository;

import me.chromiumore.tigerbank.domain.BaseEntity;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseRepository {
    protected Map<Integer, BaseEntity> storage;

    public BaseRepository() {
        storage = new HashMap<>();
    }

    public void add(BaseEntity entity) {
        storage.put(getId(), entity);
    };

    public BaseEntity get(int id) {
        return storage.get(id);
    }

    public Map<Integer, BaseEntity> getAll() {
        return new HashMap<>(storage);
    }

    public void update(BaseEntity entity) {
        int id = getId();
        storage.replace(id, entity);
    }

    public void remove(int id) {
        storage.remove(id);
    }

    public boolean contains(int id) {
        return storage.containsKey(id);
    }

    protected abstract int getId();
}
