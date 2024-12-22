package com.exclusive.exclusive.entity;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double rate;

    @Column(nullable = false)
    private int quantity;

    // utilities methods

     /**
     * To add a like
     * @param like
     */
     public void addLike(Like like) {
        likes.add(like);
        like.setProduct(this);
    }

    /**
     * To remove a like
     * @param like
     */
    public void removeLike(Like like) {
        likes.remove(like);
        like.setProduct(null);
    }

    /**
     * To add an orderProduct
     * @param orderProduct
     */
    public void addOrderProduct(OrderProduct orderProduct) {
        orderProducts.add(orderProduct);
        orderProduct.setProduct(this);
    }

    /**
     * To remove an orderProduct
     * @param orderProduct
     */
    public void removeOrderProduct(OrderProduct orderProduct) {
        orderProducts.remove(orderProduct);
        orderProduct.setProduct(null);
    }
}
