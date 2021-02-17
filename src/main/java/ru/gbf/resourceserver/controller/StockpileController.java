package ru.gbf.resourceserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gbf.resourceserver.dto.StockGoodDto;
import ru.gbf.resourceserver.service.StockGoodService;

import java.util.List;

@RestController
@RequestMapping("/api/stockpile")
@AllArgsConstructor
public class StockpileController {

    private final StockGoodService stockGoodService;

    /**
     * Возвращает количестов товаров данного вида
     * @param dto
     * @return
     */
    @PostMapping("/check")
    public Long getAllGoodsOfStock(@RequestBody StockGoodDto dto) {
        return stockGoodService.check(dto);
    }

    /**
     * Заполняет склад
     * @param dto
     */
    @PostMapping("/fill")
    @ResponseStatus(HttpStatus.OK)
    public void fill(@RequestBody List<StockGoodDto> dto) {
        stockGoodService.fill(dto);
    }

    /**
     * Забирает товары в заказ
     * @param dto
     */
    @PostMapping("/order")
    @ResponseStatus(HttpStatus.OK)
    public void order(@RequestBody List<StockGoodDto> dto) {
        stockGoodService.order(dto);
    }
}
