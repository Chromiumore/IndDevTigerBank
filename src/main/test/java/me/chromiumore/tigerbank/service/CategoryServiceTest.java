package me.chromiumore.tigerbank.service;

import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.Category;
import me.chromiumore.tigerbank.domain.OperationType;
import me.chromiumore.tigerbank.domain.param.CategoryParam;
import me.chromiumore.tigerbank.repository.CategoryRepository;
import me.chromiumore.tigerbank.service.entity.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    private CategoryService service;
    @Autowired
    private CategoryRepository repository;

    @Test
    @DisplayName("Добавление категории через сервис")
    public void addCategoryTest() {
        CategoryParam param = new CategoryParam(
                OperationType.INCOME,
                "AddCat"
        );
        int id = service.create(param);

        BaseEntity entity = repository.get(id);
        assertNotNull(entity);
        assertInstanceOf(Category.class, entity);

        Category category = (Category) entity;
        assertEquals(param.getName(), category.getName());
        assertEquals(param.getType(), category.getType());
    }

    @Test
    @DisplayName("Редактирование категории через сервис")
    public void updateCategoryTest() {
        CategoryParam param = new CategoryParam(
                OperationType.EXPENSE,
                "OldCat"
        );
        int id = service.create(param);
        assertNotNull(repository.get(id));

        Category category = new Category(OperationType.INCOME, "UpdateAcc");
        service.update(id, category);

        BaseEntity updatedEntity = repository.get(id);

        assertInstanceOf(Category.class, updatedEntity);
        Category updatedCategory = (Category) updatedEntity;

        assertEquals(category.getName(), updatedCategory.getName());
        assertEquals(category.getType(), updatedCategory.getType());
    }

    @Test
    @DisplayName("Получение категории через сервис")
    public void getCategoryTest() {
        CategoryParam param = new CategoryParam(
                OperationType.INCOME,
                "GetCat"
        );
        int id = service.create(param);

        BaseEntity entity = service.get(id);
        assertNotNull(entity);
        assertInstanceOf(Category.class, entity);

        Category category = (Category) entity;
        assertEquals(param.getName(), category.getName());
        assertEquals(param.getType(), category.getType());
    }

    @Test
    @DisplayName("Удаление категории через сервис")
    public void deleteCategoryTest() {
        CategoryParam param = new CategoryParam(
                OperationType.INCOME,
                "DeleteCat"
        );
        int id = service.create(param);

        service.delete(id);
        assertNull(service.get(id));
        assertFalse(repository.contains(id));
    }
}
