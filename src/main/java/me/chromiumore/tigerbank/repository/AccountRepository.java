package me.chromiumore.tigerbank.repository;

import me.chromiumore.tigerbank.domain.BankAccount;
import org.springframework.stereotype.Service;

@Service
public class AccountRepository extends BaseRepository {
    private static int nextId = 0;

    @Override
    protected int getId() {
        int result = nextId;
        nextId++;
        return result;
    }
}
