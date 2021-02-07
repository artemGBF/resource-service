package ru.gbf.resourceserver.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("good_stock")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoodStock {
    @Id
    private Long id;
    private Long idGood;
    private Long idStock;
    private Integer count;
}



