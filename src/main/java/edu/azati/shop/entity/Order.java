package edu.azati.shop.entity;

import edu.azati.shop.enums.PaymentType;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@With
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "paymentType")
    private PaymentType paymentType;
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Product> products = new ArrayList<>();
}
