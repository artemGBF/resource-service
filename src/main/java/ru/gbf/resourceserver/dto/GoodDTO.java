package ru.gbf.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.gbf.resourceserver.model.Category;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GoodDTO {
    private Long id;
    private String name;
    private Integer price;
    private Category category;
}
