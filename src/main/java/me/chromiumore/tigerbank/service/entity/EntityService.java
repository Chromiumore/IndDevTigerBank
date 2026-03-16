package me.chromiumore.tigerbank.service.entity;

import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.param.EntityParam;

public interface EntityService {
    int create(EntityParam param);
    BaseEntity get(int id);
    void update(int id, BaseEntity entity);
    void delete(int id);
}
