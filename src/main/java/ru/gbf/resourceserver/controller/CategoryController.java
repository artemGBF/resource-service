package ru.gbf.resourceserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.MethodNotAllowedException;
import ru.gbf.resourceserver.dto.CategoryDTO;
import ru.gbf.resourceserver.service.CategoryService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/categories", produces = "application/json")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @GetMapping
    @Operation(summary = "Получение всех категорий")
    public List<CategoryDTO> getAll() {
        return categoryService.getAll().stream()
                .map(m -> modelMapper.map(m, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение категории по id")
    public CategoryDTO findById(@PathVariable Long id) {
        throw new MethodNotAllowedException(HttpMethod.GET, Collections.singleton(HttpMethod.GET));
    }
}
