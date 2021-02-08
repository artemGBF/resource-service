package ru.gbf.resourceserver.controller;

import lombok.AllArgsConstructor;
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
import ru.gbf.resourceserver.model.Good;
import ru.gbf.resourceserver.service.GoodService;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/api/goods")
public class GoodController {

    private final GoodService goodService;

    @GetMapping
    public ResponseEntity<Collection<Good>> getAllByCategory(@Param("idCategory") Long idCategory) {
        return ResponseEntity.ok(goodService.findAllByCategory(idCategory));
    }

    @GetMapping("/{id}")
    public Good getById(@PathVariable Long id) {
        return goodService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Good create(@RequestBody Good good) {
        return goodService.save(good);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Good update(@RequestBody Good good) {
        return goodService.update(good);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(@Param(value = "id") Long id) {
        goodService.delete(id);
    }
}
