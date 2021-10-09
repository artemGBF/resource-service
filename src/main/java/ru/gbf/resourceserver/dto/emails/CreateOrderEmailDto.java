package ru.gbf.resourceserver.dto.emails;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbf.resourceserver.dto.GoodDTO;
import ru.gbf.resourceserver.meta.CreateOrderEmailDtoSerializer;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonSerialize(using = CreateOrderEmailDtoSerializer.class)
public class CreateOrderEmailDto extends EmailDto {
    private String delivery;
    private Long orderId;
    private Map<GoodDTO, Integer> goods;

    public CreateOrderEmailDto(String to, String delivery, Long orderId, Map<GoodDTO, Integer> goods) {
        super(to);
        this.delivery = delivery;
        this.orderId = orderId;
        this.goods = goods;
    }
}