package me.chromiumore.tigerbank.factory;

import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.param.EntityParam;

public interface EntityFactory {
    BaseEntity createEntity(EntityParam param);
}
