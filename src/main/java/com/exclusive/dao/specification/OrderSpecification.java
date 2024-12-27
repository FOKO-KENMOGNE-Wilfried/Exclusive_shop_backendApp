package com.exclusive.dao.specification;

import org.springframework.data.jpa.domain.Specification;

import com.exclusive.exclusive.entity.Order;
import com.exclusive.exclusive.entity.User;

public class OrderSpecification {

  public static Specification<Order> hasIsOrder(boolean isOrder) {
    return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("isOrder"), isOrder);
  }

  public static Specification<Order> hasUser(User user) {
    return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("user"), user);
  }

}
