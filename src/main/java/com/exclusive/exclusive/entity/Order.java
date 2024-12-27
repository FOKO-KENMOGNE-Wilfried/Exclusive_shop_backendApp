package com.exclusive.exclusive.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderProduct> orderProduct = new ArrayList<>();

    // @Column(nullable = false)
    // private double totalPrice;

    // @Column(nullable = false)
    // private int quantity;

    @Column(nullable = false)
    private boolean isValidated = false;

    @Column(nullable = false)
    private boolean isOrder = false;

}
