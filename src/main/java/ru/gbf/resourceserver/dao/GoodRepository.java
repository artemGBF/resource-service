package ru.gbf.resourceserver.dao;


import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.gbf.resourceserver.model.Good;

import java.util.List;

public interface GoodRepository extends CrudRepository<Good, Long> {

    List<Good> findAllByCategoryID(Long id);

    @Modifying
    @Query("update good set name = :name, price = :price, category_id = :idc where id=:id")
    void update(@Param("id") Long id, @Param("name") String name, @Param("price") Integer price, @Param("idc") Long idc);
}