package ru.gbf.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddItemsDTO {
    private Long goodId;
    private Long cartId;
    private Integer count;
}
