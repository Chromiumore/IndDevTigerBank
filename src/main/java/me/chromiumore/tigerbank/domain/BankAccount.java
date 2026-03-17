package me.chromiumore.tigerbank.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class BankAccount extends BaseEntity{
    @Getter @Setter
    private String name;
    @Getter @Setter
    private double balance;

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Сумма пополнения может быть только положительной");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Сумма списания может быть только положительной");
        }
        if (amount > this.balance) {
            throw new RuntimeException("Недостаточно средств на счете");
        }
        this.balance -= amount;
    }
}
