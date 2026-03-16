package me.chromiumore.tigerbank.domain;

import lombok.Getter;
import lombok.Setter;

public class Category extends BaseEntity {
    @Getter @Setter
    private OperationType type;
    @Getter @Setter
    private String name;

    public Category(OperationType type, String name) {
        this.type = type;
        this.name = name;
    }
}
