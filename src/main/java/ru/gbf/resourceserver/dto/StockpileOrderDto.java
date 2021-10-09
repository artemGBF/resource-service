package ru.gbf.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbf.resourceserver.types.DeliveryType;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockpileOrderDto {
    private String address;
    private DeliveryType deliveryType;
    private List<OrderGoodsDTO> cartGoodList;
}
