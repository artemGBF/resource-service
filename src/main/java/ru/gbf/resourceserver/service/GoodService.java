package ru.gbf.resourceserver.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gbf.resourceserver.errors.ResourceNotFoundException;
import ru.gbf.resourceserver.model.Good;
import ru.gbf.resourceserver.repository.GoodRepository;

import java.util.Collection;

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

    public Good save(Good Good) {
        if (Good.getId() == null) {
            return goodRepository.save(Good);
        }
        return null;
    }

    public void delete(Long id) {
        this.goodRepository.deleteById(id);
    }

    //@Transactional
    public Good update(Long id, Good Good) {
        goodRepository.update(id, Good.getName(), Good.getPrice(), Good.getCategoryID());
        return goodRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Good.getName(), id));
    }
}
