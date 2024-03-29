package ru.gbf.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodDTO implements Serializable {
    private Long id;
    private String name;
    private Integer price;
    private Long category;
}
