package me.chromiumore.tigerbank.service.entity;

import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.Category;
import me.chromiumore.tigerbank.domain.param.EntityParam;
import me.chromiumore.tigerbank.factory.CategoryFactory;
import me.chromiumore.tigerbank.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements EntityService {
    @Autowired
    private CategoryRepository repository;
    @Autowired
    private CategoryFactory factory;


    @Override
    public int create(EntityParam param) {
        Category category = (Category) factory.createEntity(param);
        return repository.add(category);
    }

    @Override
    public BaseEntity get(int id) {
        return repository.get(id);
    }

    @Override
    public void update(int id, BaseEntity entity) {
        repository.update(id, (Category) entity);
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }
}
