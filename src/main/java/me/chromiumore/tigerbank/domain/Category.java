package me.chromiumore.tigerbank.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {
    @Getter @Setter
    private OperationType type;
    @Getter @Setter
    private String name;
}
