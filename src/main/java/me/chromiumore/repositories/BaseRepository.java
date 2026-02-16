package me.chromiumore.repositories;

import me.chromiumore.entities.BaseEntity;

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

    public void update(int id, T entity) {
        if (id != entity.getId()) {
            throw new RuntimeException("ID сущности не совпадает c ключом!");
        }
        storage.replace(id, entity);
    }

    public void remove(int id) {
        storage.remove(id);
    }

    public boolean contains(int id) {
        return storage.containsKey(id);
    }
}
