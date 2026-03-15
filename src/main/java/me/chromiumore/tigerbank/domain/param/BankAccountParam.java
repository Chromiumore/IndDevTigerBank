package me.chromiumore.tigerbank.domain.param;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BankAccountParam extends EntityParam {
    private String name;
    private double balance;

    public BankAccountParam(String name, double balance) {
        super(EntityType.BANK_ACCOUNT);
        this.name = name;
        this.balance = balance;
    }
}
