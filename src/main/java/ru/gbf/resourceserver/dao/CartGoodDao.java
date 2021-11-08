package ru.gbf.resourceserver.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.gbf.resourceserver.model.OrderGoods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CartGoodDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void fill(List<OrderGoods> collect) {
        Map<String, Object>[] init = init(collect);
        jdbcTemplate.batchUpdate(
                "INSERT INTO xref_order_2_goods VALUES (:order, :good, :count)" +
                        "ON CONFLICT(order_id, good_id) DO UPDATE SET count = :count + " +
                        "(select count from xref_order_2_goods cd where cd.good_id = :good and cd.order_id = :order);",
                init
        );
    }

    public void clear(Long orderId) {
        Map<String, Object> init = Map.of("orderId", orderId);
        jdbcTemplate.update(
                "delete from xref_order_2_goods where order_id = :orderId;",
                init
        );
    }

    public List<OrderGoods> getAllByIdCartEquals(Long orderId) {
        Map<String, Long> init = new HashMap<>();
        init.put("orderId", orderId);
        return jdbcTemplate.query(
                "select * from xref_order_2_goods where order_id=:orderId",
                init,
                (resultSet, i) -> new OrderGoods(
                        resultSet.getLong("order_id"),
                        resultSet.getLong("good_id"),
                        resultSet.getInt("count")
                )
        );
    }

    private Map<String, Object>[] init(List<OrderGoods> list) {
        Map<String, Object>[] params = new Map[list.size()];
        for (int i = 0; i < list.size(); i++) {
            params[i] = new HashMap<>();
            OrderGoods orderGoods = list.get(i);
            params[i].put("order", orderGoods.getOrderId());
            params[i].put("good", orderGoods.getGoodId());
            params[i].put("count", orderGoods.getCount());
        }
        return params;
    }
}
