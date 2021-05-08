package ru.gbf.resourceserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gbf.resourceserver.dto.CreateOrderEmailDto;
import ru.gbf.resourceserver.dto.GoodDTO;
import ru.gbf.resourceserver.dto.OrderDTO;
import ru.gbf.resourceserver.model.Order;
import ru.gbf.resourceserver.service.OrderService;

import java.net.URISyntaxException;
import java.util.HashMap;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    @Operation(summary = "Создание заказа")
    public Order add(@RequestBody OrderDTO dto) throws URISyntaxException {
        return service.create(dto);
    }


}
