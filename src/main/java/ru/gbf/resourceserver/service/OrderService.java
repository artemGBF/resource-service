package ru.gbf.resourceserver.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gbf.resourceserver.dto.CountDto;
import ru.gbf.resourceserver.dto.CreateOrderEmailDto;
import ru.gbf.resourceserver.dto.GoodDTO;
import ru.gbf.resourceserver.dto.OrderDTO;
import ru.gbf.resourceserver.dto.StockGoodDto;
import ru.gbf.resourceserver.errors.ResourceLackException;
import ru.gbf.resourceserver.model.CartGood;
import ru.gbf.resourceserver.model.Good;
import ru.gbf.resourceserver.model.Order;
import ru.gbf.resourceserver.dao.OrderRepository;
import ru.gbf.resourceserver.types.OrderStatus;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CartService cartService;
    private final GoodService goodService;
    private final JmsTemplate jmsTemplate;
    private final ModelMapper mapper;
    @Value("${email.queue}")
    private String emailQueue;
    @Value("${order.queue}")
    private String orderQueue;

    private final RestTemplate restTemplate = new RestTemplate();

    public Order create(OrderDTO dto) throws URISyntaxException {
        List<CartGood> goods = cartService.getAllFromCart(dto.getIdCart()).getCartGood();

        List<Long> collect = goods.stream().map(CartGood::getIdGood).collect(Collectors.toList());
        List<CountDto> countOfGoods = getCountOfGoods(collect);

        for (int i = 0; i < goods.size(); i++) {
            Long idGood = goods.get(i).getIdGood();
            int count = goods.get(i).getCount();
            if (countOfGoods.get(i).getCount() < count) {
                throw new ResourceLackException(idGood, count);
            }
        }

        List<Good> byIds = goodService.findByIds(collect);

        Map<GoodDTO, Integer> emailMap = createEmailMap(byIds, goods);

        cartService.clear(dto.getIdCart());

        Order newOrder = repository.save(new Order(
                        null,
                        UUID.randomUUID().toString(),
                        dto.getIdUser(),
                        dto.getIdCart(),
                        dto.getDeliveryType(),
                        dto.getPayType(),
                        OrderStatus.PAYED
                )
        );

        orderGoodsFromStockpile(goods);

        //sendToQueue(orderQueue, dto, newOrder);
        sendToQueue(emailQueue, dto, newOrder, emailMap);
        return newOrder;
    }

    private Map<GoodDTO, Integer> createEmailMap(List<Good> goods, List<CartGood> cartGoods) {
        Map<GoodDTO, Integer> emailMap = new HashMap<>();
        for (int i = 0; i < goods.size(); i++) {
            Good good = goods.get(i);
            int count = 0;
            for (int j = 0; j < cartGoods.size(); j++) {
                CartGood cartGood = cartGoods.get(j);
                if (cartGood.getIdGood().equals(good.getId())) {
                    count = cartGood.getCount();
                    break;
                }
            }
            emailMap.put(
                    mapper.map(good, GoodDTO.class),
                    count
            );
        }
        return emailMap;
    }

    private void sendToQueue(String queue, OrderDTO dto, Order newOrder, Map<GoodDTO, Integer> map) {
        jmsTemplate.convertAndSend(queue, new CreateOrderEmailDto(
                "Emails",
                newOrder.getUuid(),
                newOrder.getDelivery().name(),
                dto.getIdCart(),
                map
        ));
    }

    //todo yandex.maps via address
    private void orderGoodsFromStockpile(List<CartGood> goods) {
        List<StockGoodDto> stockGoodDtos = new ArrayList<>();
        for (int i = 0; i < goods.size(); i++) {
            CartGood cartGood = goods.get(i);
            stockGoodDtos.add(
                    new StockGoodDto(
                            cartGood.getIdGood(),
                            1L,
                            cartGood.getCount()
                    )
            );
        }
        restTemplate.postForObject(
                "http://localhost:8082/api/stockpile/order",
                stockGoodDtos,
                Void.class
        );
    }

    private List<CountDto> getCountOfGoods(List<Long> collect) {
        return Arrays.asList(restTemplate.postForObject(
                "http://localhost:8082/api/stockpile/checkAllCount",
                collect,
                CountDto[].class
        ));
    }

}
