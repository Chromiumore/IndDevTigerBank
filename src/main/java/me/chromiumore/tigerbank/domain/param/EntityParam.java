package me.chromiumore.tigerbank.domain.param;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class EntityParam {
    private EntityType entityType;

    public EntityParam(EntityType entityType) {
        this.entityType = entityType;
    }
}
