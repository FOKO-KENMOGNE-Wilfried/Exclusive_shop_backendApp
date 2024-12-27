package com.exclusive.exclusive.service;

import com.exclusive.exclusive.entity.OrderProduct;

public interface IOrderProductService {

  public OrderProduct AddOrderProduct(OrderProduct orderProduct);

  public OrderProduct updateOrderProduct(Long ordeProductrId);

  public void deleteOrderProduct(Long orderProductId);

}
