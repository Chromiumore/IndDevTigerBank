package me.chromiumore.tigerbank.entitie;

import lombok.Getter;

@Getter
public abstract class BaseEntity {
    private final int id;

    public BaseEntity(int id) {
        this.id = id;
    }
}
