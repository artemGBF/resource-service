package ru.gbf.resourceserver.mapper;

import org.springframework.stereotype.Component;
import ru.gbf.resourceserver.dto.GoodStockDto;
import ru.gbf.resourceserver.model.GoodStock;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GoodStockMapper {

    public GoodStockDto toDto(GoodStock goodStock) {
        return new GoodStockDto(
                goodStock.getIdGood(),
                goodStock.getIdStock(),
                goodStock.getCount()
        );
    }

    public List<GoodStockDto> toDtos(List<GoodStock> goodStock) {
        return goodStock.stream().map(this::toDto).collect(Collectors.toList());
    }

    public GoodStock toEntity(GoodStockDto dto){
        return new GoodStock(
                dto.getIdGood(),
                dto.getIdStock(),
                dto.getCount()
        );
    }
}
