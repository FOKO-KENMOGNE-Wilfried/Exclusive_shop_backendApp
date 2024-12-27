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

  @OneToMany(mappedBy = "productOptions", orphanRemoval = true)
  @JsonManagedReference
  private List<ProductOptionsImages> imagesUrl = new ArrayList<>();

  @OneToMany(mappedBy = "productOptions", orphanRemoval = true)
  @JsonManagedReference
  private List<ProductOptionsSize> sizes = new ArrayList<>();

  @ManyToOne(cascade = CascadeType.ALL, optional = false)
  @JoinColumn(name = "product_id")
  @JsonBackReference
  private Product product;

  @Column(nullable = true)
  private String color;

  // @Column(nullable = true)
  // private String size;

}
