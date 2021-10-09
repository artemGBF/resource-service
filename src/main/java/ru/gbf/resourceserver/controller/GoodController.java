package ru.gbf.resourceserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gbf.resourceserver.dto.GoodDTO;
import ru.gbf.resourceserver.model.Good;
import ru.gbf.resourceserver.service.GoodService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/goods")
public class GoodController {
    private final GoodService goodService;
    private final ModelMapper mapper;

    @GetMapping
    @Operation(summary = "Получение всех товаров данной категории")
    public List<GoodDTO> getAllByCategory(@Param("idCategory") Long idCategory) {
        return goodService.findAllByCategory(idCategory).stream()
                        .map(m -> mapper.map(m, GoodDTO.class))
                        .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение товара по id")
    public GoodDTO getById(@PathVariable Long id) {
        return mapper.map(
                goodService.findById(id),
                GoodDTO.class
        );
    }

    @GetMapping("/ids")
    @Operation(summary = "Получение товара по id")
    public List<GoodDTO> getByIds(@RequestBody List<Long> ids) {
        return goodService.findByIds(ids).stream()
                .map(m -> mapper.map(m, GoodDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создание продукта")
    public GoodDTO create(@RequestBody GoodDTO good) {
        return mapper.map(
                goodService.save(mapper.map(good, Good.class)),
                GoodDTO.class);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновление продукта")
    public GoodDTO update(@RequestBody Good good) {
        return mapper.map(
                goodService.update(mapper.map(good, Good.class)),
                GoodDTO.class);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Удаление продукта")
    public void deleteById(@Param(value = "id") Long id) {
        goodService.delete(id);
    }
}
