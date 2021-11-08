package ru.gbf.resourceserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gbf.resourceserver.dto.AddItemsDTO;
import ru.gbf.resourceserver.dto.OrderGoodsDTO;
import ru.gbf.resourceserver.dto.OrderDTO;
import ru.gbf.resourceserver.model.Order;
import ru.gbf.resourceserver.model.OrderGoods;
import ru.gbf.resourceserver.service.OrderService;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper mapper;

    @GetMapping("/{id}")
    @Operation(summary = "Получение текущего заказа пользователя")
    public OrderDTO createOrGetOrder(@PathVariable Long userId) {
        Order order = orderService.createOrGet(userId);
        List<OrderGoods> goodsFromCart = orderService.getGoods(order.getId());

        OrderDTO orderDTO = mapper.map(order, OrderDTO.class);
        orderDTO.setOrderGoods(goodsFromCart.parallelStream()
                .map(m -> mapper.map(m, OrderGoodsDTO.class))
                .collect(Collectors.toList())
        );

        return orderDTO;
    }

    @PostMapping("/addFirstItem")
    @Operation(summary = "Первичное добавление товара")
    public void addFirstItem(@RequestBody AddItemsDTO good) {
        orderService.addGood(good.getGoodId(), good.getCartId());
    }

    @PostMapping("/addBatchItems")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Оптовое добавление товаров")
    public void addGoods(@RequestBody AddItemsDTO goods) {
        orderService.addGoods(goods);
    }

    @PatchMapping("/clear")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    @Operation(summary = "Очищение корзины")
    public void clear(@RequestBody Long cartId) {
        orderService.clear(cartId);
    }

    @PostMapping
    @Operation(summary = "Создание заказа")
    public OrderDTO acceptOrder(@RequestBody OrderDTO dto) throws URISyntaxException {
        return mapper.map(
                orderService.createOrGet(mapper.map(dto, Order.class)),
                OrderDTO.class
        );
    }


}
