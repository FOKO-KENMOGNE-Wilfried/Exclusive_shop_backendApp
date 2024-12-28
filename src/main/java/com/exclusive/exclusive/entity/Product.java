package com.exclusive.exclusive.entity;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private double review;

    @Column(nullable = false)
    private double promotionPrice;

    @Column(nullable = false)
    private double reviewNumber;

    @Column(nullable = false)
    private int quantity;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductOptions> options = new ArrayList<>();

    // @ManyToOne
    // @JoinColumn(name = "category_id", nullable = false)
    // private Category category;

    @Column(nullable = false)
    // @JoinColumn(name = "category_id", nullable = false)
    private Integer category_id;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductsLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderProduct> orderProducts = new ArrayList<>();

    // utilities methods

     /**
     * To add a like
     * @param like
     */
     public void addLike(ProductsLike like) {
        likes.add(like);
        like.setProduct(this);
    }

    /**
     * To remove a like
     * @param like
     */
    public void removeLike(ProductsLike like) {
        likes.remove(like);
        like.setProduct(null);
    }

    /**
     * To add an orderProduct
     * @param orderProduct
     */
    public void addOrderProduct(OrderProduct orderProduct) {
        orderProducts.add(orderProduct);
        // orderProduct.setProduct(this);
    }

    /**
     * To remove an orderProduct
     * @param orderProduct
     */
    public void removeOrderProduct(OrderProduct orderProduct) {
        orderProducts.remove(orderProduct);
        // orderProduct.setProduct(null);
    }
}
