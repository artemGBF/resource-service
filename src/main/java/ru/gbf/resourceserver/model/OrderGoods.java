package ru.gbf.resourceserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderGoods {
    private Long orderId;
    private Long goodId;
    private int count;
}
