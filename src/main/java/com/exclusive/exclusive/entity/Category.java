package com.exclusive.exclusive.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProduct = new ArrayList<>();

    /**
     * To get the category Id
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To get the category Name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * To set the category Id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To set the category Name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

}
