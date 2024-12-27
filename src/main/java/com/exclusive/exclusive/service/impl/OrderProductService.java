package com.exclusive.exclusive.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exclusive.exclusive.entity.OrderProduct;
import com.exclusive.exclusive.repository.OrderProductRepository;
import com.exclusive.exclusive.service.IOrderProductService;

@Service
public class OrderProductService implements IOrderProductService {

  private final OrderProductRepository orderProductRepository;

  @Autowired
  public OrderProductService(OrderProductRepository orderProductRepository) {
    this.orderProductRepository = orderProductRepository;
  }

  @Override
  public OrderProduct AddOrderProduct(OrderProduct orderProduct) {
    return orderProductRepository.save(orderProduct);
  }

  @Override
  public OrderProduct updateOrderProduct(Long ordeProductrId) {
    Optional<OrderProduct> existingOrderProduct = orderProductRepository.findById(ordeProductrId);
    if (existingOrderProduct.isPresent()) {
      OrderProduct orderProduct = existingOrderProduct.get();
      orderProduct.setProduct(null);
      return orderProductRepository.save(orderProduct);
    } else {
      throw new RuntimeException("Order product not found");
    }
  }

  @Override
  public void deleteOrderProduct(Long orderProductId) {
    orderProductRepository.deleteById(orderProductId);
  }

}
