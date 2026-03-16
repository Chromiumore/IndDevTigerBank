package me.chromiumore.tigerbank.repository;

import me.chromiumore.tigerbank.domain.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryRepository extends BaseRepository {
    private static int nextId = 0;

    @Override
    protected int getId() {
        int result = nextId;
        nextId++;
        return result;
    }
}
