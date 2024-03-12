package model;

import lombok.Getter;
import lombok.Setter;
import model.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Status status;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "date_of_order")
    private LocalDateTime dateOfOrder;
}