package com.exclusive.exclusive.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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

    /**
     * To get the product Id
     * @return id
     */
    public Long getId(){
        return id;
    }

    /**
     * To get the Product
     * @return product
     */
    public Product getProduct(){
        return product;
    }

    // /**
    //  * To get the Category
    //  * @return category
    //  */
    // public Category getCategory(){
    //     return category;
    // }

    /**
     * To get the Quantity
     * @return quantity
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * To get the Price
     * @return price
     */
    public double getPrice(){
        return price;
    }

    /**
     * To set the Id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To set the Product
     * @param product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    // /**
    //  * To set the Category
    //  * @param category
    //  */
    // public void setCategory(Category category) {
    //     this.category = category;
    // }

    /**
     * To set the Quantity
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * To set the Price
     * @param price
     */
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be negative");
        }
        this.price = price;
    }

}
