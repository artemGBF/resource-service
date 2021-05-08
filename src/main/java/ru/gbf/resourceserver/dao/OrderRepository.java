package ru.gbf.resourceserver.dao;


import org.springframework.data.repository.CrudRepository;
import ru.gbf.resourceserver.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
