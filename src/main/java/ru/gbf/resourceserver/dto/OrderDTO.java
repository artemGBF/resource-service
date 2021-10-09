package ru.gbf.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbf.resourceserver.model.Address;
import ru.gbf.resourceserver.model.OrderGoods;
import ru.gbf.resourceserver.types.DeliveryType;
import ru.gbf.resourceserver.types.PayType;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private Long id;
    private Long userId;
    private Address originAddr;
    private Address destinationAddr;
    private DeliveryType deliveryType;
    private PayType payType;

    private List<OrderGoodsDTO> orderGoods;
}
