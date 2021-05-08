package ru.gbf.resourceserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.gbf.resourceserver.types.DeliveryType;
import ru.gbf.resourceserver.types.OrderStatus;
import ru.gbf.resourceserver.types.PayType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table("orders")
public class Order {
    @Id
    private Long id;
    private String uuid;
    private Long idUser;
    private Long idCart;
    private DeliveryType delivery;
    @Column("paytype")
    private PayType payType;
    private OrderStatus status;
}
