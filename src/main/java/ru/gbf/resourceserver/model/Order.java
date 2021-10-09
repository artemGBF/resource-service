package ru.gbf.resourceserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.gbf.resourceserver.types.DeliveryType;
import ru.gbf.resourceserver.types.OrderStatus;
import ru.gbf.resourceserver.types.PayType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("orders")
public class Order {
    @Id
    private Long id;
    private Long idUser;
    private DeliveryType delivery;
    private PayType paymentType;
    private OrderStatus status;
    @Column("is_active")
    private boolean active;

    @Transient
    private Address originAddr;
    @Transient
    private Address destinationAddr;
}
