package ru.gbf.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbf.resourceserver.model.Cart;
import ru.gbf.resourceserver.model.CartGood;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDTO {
    private Long id;
    private String uuid;
    private List<CartGood> cartGood;
    private Long idUser;

    public CartDTO(Cart cart, List<CartGood> goods) {
        this.id = cart.getId();
        this.uuid = cart.getUuid();
        this.cartGood = goods;
        this.idUser=cart.getIdUser();
    }
}
