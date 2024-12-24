package com.exclusive.exclusive.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProductOptions {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL, optional = false)
  @JoinColumn(name = "product_id")
  @JsonBackReference
  private Product product;

  @OneToMany(mappedBy = "productOptions", orphanRemoval = true)
  @JsonManagedReference
  private List<ProductOptionsImages> productOptionsImages = new ArrayList<>();

  // @Column(nullable = false)
  // private String image;

  @Column(nullable = true)
  private String color;

  @Column(nullable = true)
  private String size;

  // @Column(nullable = false)
  // private ProductOptionsImages imagesUrl;

}
