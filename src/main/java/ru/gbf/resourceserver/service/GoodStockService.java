package ru.gbf.resourceserver.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbf.resourceserver.dto.GoodStockDto;
import ru.gbf.resourceserver.mapper.GoodStockMapper;
import ru.gbf.resourceserver.model.GoodStock;
import ru.gbf.resourceserver.repository.GoodStockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class GoodStockService {
    private final GoodStockRepository repository;
    private final GoodStockMapper mapper;

    public boolean check(GoodStockDto dto) {
        return repository.check(
                dto.getIdStock(),
                dto.getIdGood(),
                dto.getCount()
        );
    }

    public List<GoodStockDto> fill(List<GoodStockDto> dto) {
        List<GoodStock> collect = dto.stream().map(t -> new GoodStock(
                null,
                t.getIdGood(),
                t.getIdStock(),
                t.getCount()
        )).collect(Collectors.toList());
        return mapper.toDtos(
                StreamSupport.stream(repository.saveAll(collect).spliterator(), false)
                        .collect(Collectors.toList())
        );

    }
}
