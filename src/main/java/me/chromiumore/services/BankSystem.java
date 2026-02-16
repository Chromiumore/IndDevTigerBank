package me.chromiumore.services;

import me.chromiumore.repositories.AccountRepository;
import me.chromiumore.repositories.CategoryRepository;
import me.chromiumore.repositories.OperationsRepository;

public class BankSystem {
    private AccountRepository accountRepository;
    private CategoryRepository categoryRepository;
    private OperationsRepository operationsRepository;

    public BankSystem() {
        this.accountRepository = new AccountRepository();
        this.categoryRepository = new CategoryRepository();
        this.operationsRepository = new OperationsRepository();
    }
}
