package edu.azati.shop.entity;

import edu.azati.shop.enums.ProductCategory;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "productCategory")
    private ProductCategory productCategory;
    @Column(name = "price")
    private double price;
    @Column(name = "photoTitle")
    private String photoTitle;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
