package ru.gbf.resourceserver.mapper;

import org.springframework.stereotype.Component;
import ru.gbf.resourceserver.dto.CartDTO;
import ru.gbf.resourceserver.model.Cart;
import ru.gbf.resourceserver.model.CartGood;

import java.util.List;

@Component
public class CartMapper {

    public CartDTO toDTO(Cart cart, List<CartGood> goods) {
        return new CartDTO(cart, goods);
    }

   /* public Cart toEntity(CartDTO dto) {
        return new Cart(dto.getId(), dto.getUuid(), dto.getIdUser());
    }*/
}
