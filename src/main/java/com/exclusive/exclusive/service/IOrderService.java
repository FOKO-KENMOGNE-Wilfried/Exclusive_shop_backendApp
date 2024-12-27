package com.exclusive.exclusive.service;

import java.util.List;

import com.exclusive.exclusive.entity.Order;

public interface IOrderService {

  public Order AddOrder(Order order);

  public Order MakeOrder(Long orderId);

  public Order ValidateOrder(Long orderId);

  public List<Order> getOrderByUserId(Long userId);

}
