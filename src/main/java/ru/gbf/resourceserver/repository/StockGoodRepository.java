package ru.gbf.resourceserver.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.gbf.resourceserver.model.StockGood;

public interface StockGoodRepository extends CrudRepository<StockGood, Long> {

    /*@Query("select distinct case when gc.count > :count then 1 else 0 end as value" +
            " from good_stock gc where gc.id_good = :idGood and gc.id_stock=:idStock")
    Boolean check(@Param("idStock") Long idStock, @Param("idGood") Long idGood,
                  @Param("count") Integer count);*/

    @Query("select gc.count from stock_good gc where gc.id_good = :idGood and gc.id_stock=:idStock")
    Long check(@Param("idStock") Long idStock, @Param("idGood") Long idGood);

}
