package com.exclusive.exclusive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exclusive.exclusive.entity.ProductOptionsImages;

public interface ProductOptionImageRepository extends JpaRepository<ProductOptionsImages, Long> {
  
}
