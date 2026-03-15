package me.chromiumore.tigerbank.domain.param;

import lombok.Getter;
import lombok.Setter;
import me.chromiumore.tigerbank.domain.OperationType;

@Getter @Setter
public class CategoryParam extends EntityParam {
    private OperationType type;
    private String name;

    public CategoryParam(OperationType type, String name) {
        super(EntityType.CATEGORY);
        this.type = type;
        this.name = name;
    }
}
