package com.exclusive.exclusive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exclusive.exclusive.entity.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
