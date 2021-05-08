package ru.gbf.resourceserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gbf.resourceserver.dto.AddGoodsDto;
import ru.gbf.resourceserver.dto.AddOneGoodDto;
import ru.gbf.resourceserver.dto.CartDTO;
import ru.gbf.resourceserver.model.Cart;
import ru.gbf.resourceserver.service.CartService;

import java.io.IOException;


@RestController
@RequestMapping(value = "/api/cart", produces = "application/json")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @GetMapping("/{id}")
    @Operation(summary = "Получение содержимого корзины")
    public CartDTO showCart(@PathVariable Long id) {
        return service.getAllFromCart(id);
    }

    @PostMapping("/create/{idUser}")
    @Operation(summary = "Создание корзины для пользователя")
    public Cart create(@PathVariable Long idUser) {
        return service.createCart(idUser);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Первичное добавление товара")
    public void addGood(@RequestBody AddOneGoodDto goodForAddDTO) throws IOException {
        service.addGood(goodForAddDTO);
    }

    @PostMapping("/batchadd")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Оптовое добавление товаров")
    public void addGoods(@RequestBody AddGoodsDto goods) {
        service.addGoods(goods);
    }

    @PatchMapping("/clear")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    @Operation(summary = "Очищение корзины")
    public void clear(@RequestBody Long idCart) {
        service.clear(idCart);
    }
}
