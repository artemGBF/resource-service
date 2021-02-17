package ru.gbf.resourceserver.mapper;

import org.springframework.stereotype.Component;
import ru.gbf.resourceserver.dto.StockGoodDto;
import ru.gbf.resourceserver.model.StockGood;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GoodStockMapper {

    public StockGoodDto toDto(StockGood stockGood) {
        return new StockGoodDto(
                stockGood.getIdGood(),
                stockGood.getIdStock(),
                stockGood.getCount()
        );
    }

    public List<StockGoodDto> toDtos(List<StockGood> stockGood) {
        return stockGood.stream().map(this::toDto).collect(Collectors.toList());
    }

    public StockGood toEntity(StockGoodDto dto){
        return new StockGood(
                dto.getIdGood(),
                dto.getIdStock(),
                dto.getCount()
        );
    }
}
