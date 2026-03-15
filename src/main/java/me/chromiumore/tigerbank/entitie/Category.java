package me.chromiumore.tigerbank.entitie;

import lombok.Getter;
import lombok.Setter;

public class Category extends BaseEntity {
    private static int nextId = 0;
    @Getter @Setter
    private OperationType type;
    @Getter @Setter
    private String name;

    public Category(OperationType type, String name) {
        super(nextId);
        nextId++;
        this.type = type;
        this.name = name;
    }
}
