package ru.gbf.resourceserver.repository;


import org.springframework.data.repository.CrudRepository;
import ru.gbf.resourceserver.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByName(String name);
}
