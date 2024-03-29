package ru.gbf.resourceserver.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbf.resourceserver.errors.ResourceNotFoundException;
import ru.gbf.resourceserver.model.Good;
import ru.gbf.resourceserver.dao.GoodRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class GoodService {

    private final GoodRepository goodRepository;

    public Collection<Good> findAllByCategory(Long idCategory) {
        return goodRepository.findAllByCategoryID(idCategory);
    }

    public Good findById(Long id) {
        return goodRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Товар", id));
    }

    public List<Good> findByIds(List<Long> id) {
        return (List<Good>) goodRepository.findAllById(id);
    }

    public Good save(Good good) {
        if (good.getId() == null) {
            return goodRepository.save(good);
        }
        return null;
    }

    public void delete(Long id) {
        this.goodRepository.deleteById(id);
    }

    public Good update(Good good) {
        if (good.getId() == null) {
            return save(good);
        }
        return goodRepository.save(good);
    }
}
