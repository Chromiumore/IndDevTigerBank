package me.chromiumore.tigerbank.service;

import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.param.EntityParam;

public interface EntityService {
    void create(EntityParam param);
    BaseEntity get(int id);
    void update(BaseEntity entity);
    void delete(int id);
}
