package ru.gbf.resourceserver.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.gbf.resourceserver.model.CartGood;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CartGoodDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private Map<String, Object>[] init(List<CartGood> list) {
        Map<String, Object>[] params = new Map[list.size()];
        for (int i = 0; i < list.size(); i++) {
            params[i] = new HashMap<>();
            CartGood cartGood = list.get(i);
            params[i].put("cart", cartGood.getIdCart());
            params[i].put("good", cartGood.getIdGood());
            params[i].put("count", cartGood.getCount());
        }
        return params;
    }

    public void fill(List<CartGood> collect) {
        Map<String, Object>[] init = init(collect);
        jdbcTemplate.batchUpdate(
                "INSERT INTO cart_good VALUES (:cart, :good, :count)" +
                        "ON CONFLICT(id_cart, id_good) DO UPDATE SET count = :count + " +
                        "(select count from cart_good cd where cd.id_good = :good and cd.id_cart = :cart);",
                init
        );
    }

    public void clear(Long idCart) {
        Map<String, Object> init = Map.of("cart", idCart);
        jdbcTemplate.update(
                "delete from cart_good where id_cart = :cart;",
                init
        );
    }

    public void activate(Long idUser) {
        Map<String, Object> init = Map.of("user", idUser);
        jdbcTemplate.update(
                "update carts set is_active = true where id_user=:user",
                init
        );
    }

    public List<CartGood> getAllByIdCartEquals(Long idCart) {
        Map<String, Long> init = new HashMap<>();
        init.put("cart", idCart);
        return jdbcTemplate.query(
                "select * from cart_good where id_cart=:cart",
                init,
                (resultSet, i) -> new CartGood(
                        resultSet.getLong("id_cart"),
                        resultSet.getLong("id_good"),
                        resultSet.getInt("count")
                )
        );
    }
}
