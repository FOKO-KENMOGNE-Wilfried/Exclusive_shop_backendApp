package com.exclusive.exclusive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exclusive.exclusive.entity.ProductOptions;

public interface ProductOptionRepository extends JpaRepository<ProductOptions, Long> {
  
}
