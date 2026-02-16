package me.chromiumore.entities;

public class Category extends BaseEntity {
    private static int nextId = 0;
    private OperationType type;
    private String name;

    public Category(OperationType type, String name) {
        super(nextId);
        nextId++;
        this.type = type;
        this.name = name;
    }
}
