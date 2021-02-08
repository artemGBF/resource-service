package ru.gbf.resourceserver.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.gbf.resourceserver.dto.GoodStockDto;
import ru.gbf.resourceserver.mapper.GoodStockMapper;
import ru.gbf.resourceserver.model.GoodStock;
import ru.gbf.resourceserver.repository.GoodStockRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GoodStockDao {
    private final GoodStockRepository repository;
    private final GoodStockMapper mapper;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Boolean check(GoodStock goodStock) {
        return repository.check(
                goodStock.getIdStock(),
                goodStock.getIdGood(),
                goodStock.getCount()
        );
    }

    private Map<String, Object>[] init(List<GoodStock> list) {
        Map<String, Object>[] params = new Map[list.size()];
        for (int i = 0; i < list.size(); i++) {
            params[i] = new HashMap<>();
            GoodStock goodStock = list.get(i);
            params[i].put("stock", goodStock.getIdStock());
            params[i].put("good", goodStock.getIdGood());
            params[i].put("count", goodStock.getCount());
        }
        return params;
    }

    public void fill(List<GoodStock> collect) {
        Map<String, Object>[] init = init(collect);
        jdbcTemplate.batchUpdate(
                "INSERT INTO good_stock VALUES (:good, :stock, :count)" +
                        "ON CONFLICT(id_stock, id_good) DO UPDATE SET count = :count + " +
                        "(select count from good_stock gc where gc.id_good = :good and gc.id_stock = :stock);",
                init
        );
    }
}
