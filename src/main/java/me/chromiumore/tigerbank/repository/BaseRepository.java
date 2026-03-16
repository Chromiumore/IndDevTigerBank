package me.chromiumore.tigerbank.repository;

import me.chromiumore.tigerbank.domain.BaseEntity;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseRepository<T extends BaseEntity> {
    protected Map<Integer, T> storage;

    public BaseRepository() {
        storage = new HashMap<>();
    }

    public void add(T entity) {
        storage.put(entity.getId(), entity);
    };

    public T get(int id) {
        return storage.get(id);
    }

    public Map<Integer, T> getAll() {
        return new HashMap<>(storage);
    }

    public void update(T entity) {
        int id = entity.getId();
        storage.replace(id, entity);
    }

    public void remove(int id) {
        storage.remove(id);
    }

    public boolean contains(int id) {
        return storage.containsKey(id);
    }
}
