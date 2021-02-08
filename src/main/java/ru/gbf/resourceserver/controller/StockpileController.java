package ru.gbf.resourceserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gbf.resourceserver.dto.GoodStockDto;
import ru.gbf.resourceserver.model.GoodStock;
import ru.gbf.resourceserver.service.GoodStockService;

import java.util.List;

@RestController
@RequestMapping("/api/stockpile")
@AllArgsConstructor
public class StockpileController {

    private final GoodStockService goodStockService;

    @PostMapping("/check")
    public Boolean getAllGoodsOfStock(@RequestBody GoodStockDto dto) {
        return goodStockService.check(dto);
    }

    @PostMapping("/fill")
    @ResponseStatus(HttpStatus.OK)
    public void fill(@RequestBody List<GoodStockDto> dto) {
        goodStockService.fill(dto);
    }
}
