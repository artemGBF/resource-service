package ru.gbf.resourceserver.dao;


import org.springframework.data.repository.CrudRepository;
import ru.gbf.resourceserver.model.Order;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findByIdAndActiveTrue(Long id);
}
