package ru.gbf.resourceserver.service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gbf.resourceserver.dao.CartGoodDao;
import ru.gbf.resourceserver.dto.AddGoodsDto;
import ru.gbf.resourceserver.dto.AddOneGoodDto;
import ru.gbf.resourceserver.dto.CartDTO;
import ru.gbf.resourceserver.dto.CountDto;
import ru.gbf.resourceserver.errors.ResourceLackException;
import ru.gbf.resourceserver.errors.ResourceNotFoundException;
import ru.gbf.resourceserver.model.Cart;
import ru.gbf.resourceserver.model.CartGood;
import ru.gbf.resourceserver.model.StockGood;
import ru.gbf.resourceserver.dao.CartRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository repository;
    private final CartGoodDao dao;
    private final RestTemplate restTemplate = new RestTemplate();

    public Cart createCart(Long idUser) {
        return repository.findById(idUser).orElse(
                repository.save(new Cart(null,
                                UUID.randomUUID().toString(),
                                idUser,
                                true
                        )
                )
        );
    }

    //todo apache httpclient or via header
    public void addGood(AddOneGoodDto dto) throws IOException {
        StockGood stockGood = new StockGood(
                dto.getIdGood(),
                1L,
                1
        );
       /* //Cookie credentials = (Cookie) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        Header header = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        List<Header> headers = List.of(header);

        BasicCookieStore basicCookieStore = new BasicCookieStore();
        BasicClientCookie basicClientCookie1 = new BasicClientCookie("COOKIE-BEARER", credentials.getValue());
        basicClientCookie1.setExpiryDate(new Date(2021, Calendar.APRIL,4));
        basicClientCookie1.setPath("/");
        Date expiryDate = basicClientCookie1.getExpiryDate();
        System.out.println(expiryDate);
        basicCookieStore.addCookie(basicClientCookie1);

        HttpPost postRequest = new HttpPost("http://localhost:8082/api/stockpile/checkCount");
        postRequest.setEntity(new ByteArrayEntity(new ObjectMapper().writeValueAsBytes(stockGood)));

        HttpClient client = HttpClients.custom()
                .setDefaultHeaders(headers)
                .setDefaultCookieStore(basicCookieStore)
                .build();
        HttpResponse response = client.execute(postRequest);
        Long count = new ObjectMapper().readValue(response.getEntity().getContent(), Long.class);*/

        CountDto count = restTemplate.postForObject(
                "http://localhost:8082/api/stockpile/checkCount",
                stockGood,
                CountDto.class,
                (Object) null);

        if (count.getCount() == 0) {
            throw new ResourceLackException();
        }
        dao.fill(List.of(new CartGood(
                        dto.getIdCart(),
                        dto.getIdGood(),
                        1
                ))
        );
    }

    public void addGoods(AddGoodsDto dto) {
        HttpEntity<StockGood> httpEntity = new HttpEntity<>(
                new StockGood(
                        dto.getIdGood(),
                        1L,
                        dto.getCount()
                )
        );
        CountDto aLong = restTemplate.postForObject(
                "http://localhost:8082/api/stockpile/check",
                httpEntity,
                CountDto.class,
                (Object) null);
        if (aLong.getCount() - dto.getCount() <= 0) {
            throw new ResourceLackException(dto.getCount());
        }
        dao.fill(List.of(new CartGood(
                        dto.getIdCart(),
                        dto.getIdCart(),
                        dto.getCount()
                ))
        );
    }

    public CartDTO getAllFromCart(Long idCart) {
        Cart cart = repository.findByIdAndIsActiveIsTrue(idCart).orElseThrow(
                () -> new ResourceNotFoundException("корзина", idCart)
        );
        List<CartGood> byId = dao.getAllByIdCartEquals(idCart);
        return new CartDTO(cart, byId);
    }

    public void clear(Long idCart) {
        dao.clear(idCart);
    }
}
