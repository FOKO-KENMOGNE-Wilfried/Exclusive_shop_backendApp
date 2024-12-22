package com.exclusive.exclusive.entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProduct = new ArrayList<>();

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double rate;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private boolean like;

    // Constructoe, getter and setters, and other methods...

    /**
     * To get tht product Id
     * @return id
     */
    public Long getId(){
        return id;
    }

    /**
     * To get tht product Name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * To get tht product Price
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * To get tht product Quantity
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * To set the product Id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To set the product Name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * To set the product Price
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * To set the product Quantity
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
