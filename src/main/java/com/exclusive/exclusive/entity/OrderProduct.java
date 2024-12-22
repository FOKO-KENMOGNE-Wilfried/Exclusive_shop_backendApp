package com.exclusive.exclusive.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @OneToOne(cascade = CascadeType.ALL, optional = false)
    // @JoinColumn(name = "user_id")
    // private User userId;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    // @ManyToOne(cascade = CascadeType.ALL, optional = false)
    // @JoinColumn(name = "category_id")
    // private Category category;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity;

}
