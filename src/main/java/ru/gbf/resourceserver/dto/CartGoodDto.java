package ru.gbf.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartGoodDto {
    private Long idCart;
    private Long idGood;
    private int count;
}
