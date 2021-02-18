package ru.gbf.resourceserver.mapper;

import org.springframework.stereotype.Component;
import ru.gbf.resourceserver.dto.GoodDTO;
import ru.gbf.resourceserver.model.Good;

@Component
public class GoodMapper {

    public Good toEntity(GoodDTO goodDTO){
        return new Good(
                goodDTO.getId(),
                goodDTO.getName(),
                goodDTO.getPrice(),
                goodDTO.getCategory().getId()
        );
    }
}
