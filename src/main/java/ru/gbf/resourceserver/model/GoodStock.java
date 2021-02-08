package ru.gbf.resourceserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Table("good_stock")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoodStock {

    private Long idGood;
    private Long idStock;
    private Integer count;
}



