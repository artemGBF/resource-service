package ru.gbf.resourceserver.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbf.resourceserver.model.Category;
import ru.gbf.resourceserver.dao.CategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return (List<Category>) categoryRepository.findAll();
    }
}
