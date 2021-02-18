package ru.gbf.resourceserver.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbf.resourceserver.dao.StockGoodDao;
import ru.gbf.resourceserver.dto.StockGoodDto;
import ru.gbf.resourceserver.mapper.GoodStockMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockGoodService {
    private final StockGoodDao stockGoodDao;
    private final GoodStockMapper mapper;

    public Long check(StockGoodDto dto) {
        return stockGoodDao.check(mapper.toEntity(dto));
    }

    public void fill(List<StockGoodDto> dto) {
        stockGoodDao.fill(dto.stream().map(mapper::toEntity).collect(Collectors.toList()));
    }

    public void order(List<StockGoodDto> dto){
        stockGoodDao.order(dto.stream().map(mapper::toEntity).collect(Collectors.toList()));
    }

    public Map<Long, Integer> checkAll(List<Long> ids) {
        return stockGoodDao.checkAll(ids);
    }
}
