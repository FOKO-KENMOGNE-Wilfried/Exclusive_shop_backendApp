package com.exclusive.exclusive.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.exclusive.dao.specification.OrderSpecification;
import com.exclusive.exclusive.entity.Order;
import com.exclusive.exclusive.entity.User;
import com.exclusive.exclusive.repository.OrderRepository;
import com.exclusive.exclusive.repository.UserRepository;
import com.exclusive.exclusive.service.IOrderService;

@Service
public class OrderService implements IOrderService {

  private final OrderRepository orderRepository;
  private final UserRepository userRepository;

  @Autowired
  public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
    this.orderRepository = orderRepository;
    this.userRepository = userRepository;
  }

  @Override
  public Order AddOrder(Order order) {
    return orderRepository.save(order);
  }

  @Override
  public Order MakeOrder(Long orderId) {
    Optional<Order> existingOrder = orderRepository.findById(orderId);

    if (existingOrder.isPresent()) {
      Order order = existingOrder.get();
      order.setOrder(true);
      return orderRepository.save(order);
    } else {
      throw new RuntimeException("Order not found");
    }

  }

  @Override
  public Order ValidateOrder(Long orderId) {
    Optional<Order> existingOrder = orderRepository.findById(orderId);

    if (existingOrder.isPresent()) {
      Order order = existingOrder.get();
      order.setValidated(true);
      return orderRepository.save(order);
    } else {
      throw new RuntimeException("Order not found");
    }

  }

  @Override
  public List<Order> getOrderByUserId(Long userId) {

    User user = userRepository.findById(userId).get();

    Specification<Order> spec = Specification.where(null);
    spec = spec.and(OrderSpecification.hasIsOrder(false));
    spec = spec.and(OrderSpecification.hasUser(user));

    return orderRepository.findAll(spec);
  }

}
