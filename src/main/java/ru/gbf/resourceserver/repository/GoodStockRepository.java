package ru.gbf.resourceserver.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.gbf.resourceserver.model.GoodStock;

public interface GoodStockRepository extends CrudRepository<GoodStock, Long> {

    @Query("select distinct case when gc.count > :count then 1 else 0 end as value" +
            " from good_stock gc where gc.id_good = :idGood and gc.id_stock=:idStock")
    Boolean check(@Param("idStock") Long idStock, @Param("idGood") Long idGood,
                  @Param("count") Integer count);

}
