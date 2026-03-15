package me.chromiumore.tigerbank.factory;

import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.Category;
import me.chromiumore.tigerbank.domain.param.CategoryParam;
import me.chromiumore.tigerbank.domain.param.EntityParam;
import me.chromiumore.tigerbank.exception.CreateEntityException;
import me.chromiumore.tigerbank.exception.UnsupportedParameterException;
import org.springframework.stereotype.Service;

@Service
public class CategoryFactory implements EntityFactory {
    @Override
    public BaseEntity createEntity(EntityParam param) throws CreateEntityException {
        if (param instanceof CategoryParam categoryParam) {
            return new Category(
                    categoryParam.getType(),
                    categoryParam.getName()
            );
        }

        throw new UnsupportedParameterException();
    }
}
