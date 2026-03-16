package me.chromiumore.tigerbank.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class Operation extends BaseEntity {
    @Getter @Setter
    private OperationType type;
    @Getter @Setter
    private int bankAccountId;
    @Getter @Setter
    private double amount;
    @Getter @Setter
    private LocalDate date;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private int categoryId;
}
