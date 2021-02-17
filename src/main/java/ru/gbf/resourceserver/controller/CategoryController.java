package ru.gbf.resourceserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.MethodNotAllowedException;
import ru.gbf.resourceserver.model.Category;
import ru.gbf.resourceserver.service.CategoryService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Получение всех категорий")
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение категории по id")
    public Category findById(@PathVariable Long id) {
        throw new MethodNotAllowedException(HttpMethod.GET, Collections.singleton(HttpMethod.GET));
    }
}
