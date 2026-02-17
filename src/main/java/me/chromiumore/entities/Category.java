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

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
