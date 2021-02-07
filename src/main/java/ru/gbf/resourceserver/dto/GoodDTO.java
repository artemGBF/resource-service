package ru.gbf.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.gbf.resourceserver.model.Category;

@AllArgsConstructor
@NoArgsConstructor
public class GoodDTO {
    private Long id;
    private String name;
    private Integer price;
    private Category category;
}
