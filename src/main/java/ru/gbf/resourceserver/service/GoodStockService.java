package ru.gbf.resourceserver.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbf.resourceserver.dao.GoodStockDao;
import ru.gbf.resourceserver.dto.GoodStockDto;
import ru.gbf.resourceserver.mapper.GoodStockMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GoodStockService {
    private final GoodStockDao goodStockDao;
    private final GoodStockMapper mapper;


    public Boolean check(GoodStockDto dto) {
        return goodStockDao.check(mapper.toEntity(dto));
    }

    public void fill(List<GoodStockDto> dto) {
        goodStockDao.fill(
                dto.stream().map(mapper::toEntity).collect(Collectors.toList())
        );
    }
}
