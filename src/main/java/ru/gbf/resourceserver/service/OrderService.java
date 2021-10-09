package ru.gbf.resourceserver.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gbf.resourceserver.dao.CartGoodDao;
import ru.gbf.resourceserver.dao.OrderRepository;
import ru.gbf.resourceserver.dto.AddItemsDTO;
import ru.gbf.resourceserver.dto.CountDto;
import ru.gbf.resourceserver.dto.OrderDTO;
import ru.gbf.resourceserver.dto.emails.CreateOrderEmailDto;
import ru.gbf.resourceserver.dto.GoodDTO;
import ru.gbf.resourceserver.dto.StockGoodDto;
import ru.gbf.resourceserver.errors.ResourceLackException;
import ru.gbf.resourceserver.model.OrderGoods;
import ru.gbf.resourceserver.model.Good;
import ru.gbf.resourceserver.model.Order;
import ru.gbf.resourceserver.model.StockGood;
import ru.gbf.resourceserver.types.OrderStatus;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final GoodService goodService;
    private final JmsTemplate jmsTemplate;
    private final CartGoodDao dao;
    private final ModelMapper mapper;
    @Value("${queue.email}")
    private String emailQueue;
    @Value("${queue.route}")
    private String routeQueue;

    private final RestTemplate restTemplate = new RestTemplate();

    public Order createOrGet(Long idUser) {
        return repository.findByIdAndActiveTrue(idUser).orElse(
                repository.save(Order.builder().idUser(idUser).status(OrderStatus.CREATED).build())
        );
    }

    public void addGood(Long idGood, Long idCart) {
        StockGood stockGood = new StockGood(
                idGood,
                1L,
                1
        );

        CountDto count = restTemplate.postForObject(
                "http://localhost:8082/api/stockpile/checkCount",
                stockGood,
                CountDto.class,
                (Object) null);

        if (count.getCount() == 0) {
            throw new ResourceLackException();
        }
        dao.fill(List.of(new OrderGoods(
                        idGood,
                        idCart,
                        1
                ))
        );
    }

    public void addGoods(AddItemsDTO dto) {
        HttpEntity<StockGood> httpEntity = new HttpEntity<>(
                new StockGood(
                        dto.getIdGood(),
                        1L,
                        dto.getCount()
                )
        );
        CountDto aLong = restTemplate.postForObject(
                "http://localhost:8082/api/stockpile/checkCount",
                httpEntity,
                CountDto.class,
                (Object) null);
        if (aLong.getCount() - dto.getCount() <= 0) {
            throw new ResourceLackException(dto.getCount());
        }
        dao.fill(List.of(new OrderGoods(
                        dto.getIdCart(),
                        dto.getIdGood(),
                        dto.getCount()
                ))
        );
    }

    public List<OrderGoods> getGoods(Long idOrder) {
        return dao.getAllByIdCartEquals(idOrder);
    }

    public void clear(Long orderId) {
        dao.clear(orderId);
    }

    public Order createOrGet(Order order) throws URISyntaxException {
        List<OrderGoods> goods = getGoods(order.getId());

        List<Long> collect = goods.stream().map(OrderGoods::getGoodId).collect(Collectors.toList());
        List<CountDto> countOfGoods = getCountOfGoods(collect);

        for (int i = 0; i < goods.size(); i++) {
            Long idGood = goods.get(i).getGoodId();
            int count = goods.get(i).getCount();
            if (countOfGoods.get(i).getCount() < count) {
                throw new ResourceLackException(idGood, count);
            }
        }

        List<Good> byIds = goodService.findByIds(collect);

        Map<GoodDTO, Integer> goodCountMap = createEmailMap(byIds, goods);

        clear(order.getId());

        Order newOrder = repository.save(new Order(null,
                order.getIdUser(),
                order.getDelivery(),
                order.getPaymentType(),
                OrderStatus.PAYED,
                true,
                order.getOriginAddr(),
                order.getDestinationAddr())
        );

        orderGoodsFromStockpile(goods);

        sendToQueue(routeQueue, order, null);
        sendToQueue(emailQueue, order, goodCountMap);
        return newOrder;
    }

    private Map<GoodDTO, Integer> createEmailMap(List<Good> goods, List<OrderGoods> orderGoodsList) {
        Map<GoodDTO, Integer> emailMap = new HashMap<>();
        for (int i = 0; i < goods.size(); i++) {
            Good good = goods.get(i);
            int count = 0;
            for (int j = 0; j < orderGoodsList.size(); j++) {
                OrderGoods orderGoods = orderGoodsList.get(j);
                if (orderGoods.getGoodId().equals(good.getId())) {
                    count = orderGoods.getCount();
                    break;
                }
            }
            emailMap.put(mapper.map(good, GoodDTO.class), count);
        }
        return emailMap;
    }

    private void sendToQueue(String queue, Order order, Map<GoodDTO, Integer> map) {
        if (queue.equals("Emails")) {
            jmsTemplate.convertAndSend(queue, new CreateOrderEmailDto(
                    "Emails",
                    order.getDelivery().name(),
                    order.getId(),
                    map
            ));
        }
        if (queue.equals("Routes")) {
            jmsTemplate.convertAndSend(queue, mapper.map(order, OrderDTO.class));
        }
    }

    //todo yandex.maps via address
    private void orderGoodsFromStockpile(List<OrderGoods> goods) {
        List<StockGoodDto> stockGoodDtos = new ArrayList<>();
        for (int i = 0; i < goods.size(); i++) {
            OrderGoods orderGoods = goods.get(i);
            stockGoodDtos.add(
                    new StockGoodDto(
                            orderGoods.getGoodId(),
                            1L,
                            orderGoods.getCount()
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
