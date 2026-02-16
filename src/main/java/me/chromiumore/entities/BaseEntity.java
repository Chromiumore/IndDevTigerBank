package me.chromiumore.entities;

public abstract class BaseEntity {
    private final int id;

    public BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
