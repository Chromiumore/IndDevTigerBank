package me.chromiumore.tigerbank.entity;

import lombok.Getter;

@Getter
public abstract class BaseEntity {
    private final int id;

    public BaseEntity(int id) {
        this.id = id;
    }
}
