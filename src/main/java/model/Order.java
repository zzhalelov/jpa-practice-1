package model;

import lombok.Getter;
import lombok.Setter;
import model.Status;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Status status;

}