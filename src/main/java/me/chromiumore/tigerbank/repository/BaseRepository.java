package me.chromiumore.tigerbank.repository;

import me.chromiumore.tigerbank.domain.BaseEntity;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseRepository {
    protected Map<Integer, BaseEntity> storage;

    public BaseRepository() {
        storage = new HashMap<>();
    }

    public int add(BaseEntity entity) {
        int id = getId();
        storage.put(id, entity);
        return id;
    };

    public BaseEntity get(int id) {
        return storage.get(id);
    }

    public Map<Integer, BaseEntity> getAll() {
        return new HashMap<>(storage);
    }

    public void update(int id, BaseEntity entity) {
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
