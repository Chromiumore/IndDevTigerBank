package me.chromiumore.tigerbank.service;

import lombok.Getter;
import lombok.Setter;
import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.Category;
import me.chromiumore.tigerbank.domain.param.EntityParam;
import me.chromiumore.tigerbank.factory.CategoryFactory;
import me.chromiumore.tigerbank.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter @Setter
public class CategoryService implements EntityService {
    @Autowired
    private CategoryRepository repository;
    @Autowired
    private CategoryFactory factory;


    @Override
    public void create(EntityParam param) {
        Category category = (Category) factory.createEntity(param);
        repository.add(category);
    }

    @Override
    public BaseEntity get(int id) {
        return repository.get(id);
    }

    @Override
    public void update(BaseEntity entity) {
        repository.update((Category) entity);
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }
}
