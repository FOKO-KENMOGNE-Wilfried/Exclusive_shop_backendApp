package com.exclusive.exclusive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exclusive.dao.request.AddOrderOptionRequest;
import com.exclusive.exclusive.entity.Order;
import com.exclusive.exclusive.entity.OrderProduct;
import com.exclusive.exclusive.entity.Product;
import com.exclusive.exclusive.entity.User;
import com.exclusive.exclusive.repository.OrderProductRepository;
import com.exclusive.exclusive.repository.OrderRepository;
import com.exclusive.exclusive.repository.ProductRepository;
import com.exclusive.exclusive.repository.UserRepository;
import com.exclusive.exclusive.service.IOrderProductService;
import com.exclusive.exclusive.service.IOrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

  private final IOrderService iOrderService;
  private final UserRepository userRepository;
  private final OrderRepository orderRepository;
  private final IOrderProductService iOrderProductService;
  private final ProductRepository productRepository;
  private final OrderProductRepository orderProductRepository;

  @Autowired
  public OrderController(IOrderService iOrderService, OrderRepository orderRepository, UserRepository userRepository,
      ProductRepository productRepository, IOrderProductService iOrderProductService, OrderProductRepository orderProductRepository) {
    this.iOrderService = iOrderService;
    this.userRepository = userRepository;
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.iOrderProductService = iOrderProductService;
    this.orderProductRepository = orderProductRepository;
  }

  @GetMapping("/getOrderByUserId/{userId}")
  public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable Long userId) {
    List<Order> order = iOrderService.getOrderByUserId(userId);
    return ResponseEntity.ok(order);
  }

  /**
   * Create a new order
   * 
   * @param order The order to create
   * @param userId The userId who owns the order
   * @return the ResponseEntity with status 200 (ok) and with body of the new order
   */
  @PostMapping("/addOrder/{userId}")
  public ResponseEntity<String> addOrder(@RequestBody Order order, @PathVariable Long userId) {

    // Get the user into the database
    User user = userRepository.findById(userId).get();
    if (user == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product option not found");
    }

    Order newOrder = new Order();
    newOrder = order;
    newOrder.setUser(user);

    // System.out.println(order);
    iOrderService.AddOrder(newOrder);
    return ResponseEntity.ok("Order added successfully !");
  }

  /**
   * Create a new orderProduct
   * 
   * @param order The data of the orderProduct to create
   * @return the ResponseEntity with status 200 (ok) and with body of the new orderProduct
   */
  @PostMapping("/addOrderProduct")
  public ResponseEntity<String> addOrderProduct(@RequestBody AddOrderOptionRequest addOrderOptionRequest) {

    // Get the order into the database
    Order order = orderRepository.findById(addOrderOptionRequest.getOrderId()).get();
    if (order == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
    }

    // Get the product into the database
    Product product = productRepository.findById(addOrderOptionRequest.getProductId()).get();
    if (product == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    OrderProduct orderProduct = new OrderProduct();
    orderProduct.setOrder(order);
    orderProduct.setProduct(product);
    orderProduct.setQuantity(addOrderOptionRequest.getQuantity());

    iOrderProductService.AddOrderProduct(orderProduct);

    return ResponseEntity.ok("Order product saved successfully");

  }

  /**
   * Make cart to order
   * By default the order is consider as the cart and next the user transform it to an order by the validation of this
   * 
   * @param order The order to create
   * @param userId The userId who owns the order
   * @return the ResponseEntity with status 200 (ok) and with body "Cart make to order successfully !"
   */
  @PutMapping("/makeOrder/{orderId}")
  public ResponseEntity<String> makeOrder(@PathVariable Long orderId) {
    iOrderService.MakeOrder(orderId);
    return ResponseEntity.ok("Cart make to order successfully !");
  }

  /**
   * Validate  the order
   * 
   * @param order The order to create
   * @param userId The userId who owns the order
   * @return the ResponseEntity with status 200 (ok) and with body "Order validate successfully !"
   */
  @PutMapping("/validateOrder/{orderId}")
  public ResponseEntity<String> validateOrder(@PathVariable Long orderId) {
    iOrderService.ValidateOrder(orderId);
    return ResponseEntity.ok("Order validate successfully !");
  }

  /**
   * Update the orderProduct quantity by add 1
   * 
   * @param orderProductId The userId who owns the order
   * @return the ResponseEntity with status 200 (ok) and with body "Order quantity update successfully !"
   */
  @PutMapping("addOrderProductQuantity/{orderProductId}")
  public ResponseEntity<String> addOrderProductQuantity(@PathVariable Long orderProductId) {

    OrderProduct orderProduct = orderProductRepository.findById(orderProductId).get();
    Product product = productRepository.findById(orderProduct.getProduct().getId()).get();

    if (product.getQuantity() > 0) {
      product.setQuantity(product.getQuantity()-1);
      productRepository.save(product);
      orderProduct.setQuantity(orderProduct.getQuantity()+1);
      orderProduct.setProduct(product);
      orderProductRepository.save(orderProduct);
      return ResponseEntity.ok("Order quantity update successfully !");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not enough product quantity to add");
    }
  }

  /**
   * Update the orderProduct quantity by decrease 1
   * 
   * @param orderProductId The userId who owns the order
   * @return the ResponseEntity with status 200 (ok) and with body "Order quantity update successfully !"
   */
  @PutMapping("removeOrderProductQuantity/{orderProductId}")
  public ResponseEntity<String> removeOrderProductQuantity(@PathVariable Long orderProductId) {

    OrderProduct orderProduct = orderProductRepository.findById(orderProductId).get();
    Product product = productRepository.findById(orderProduct.getProduct().getId()).get();

    if (orderProduct.getQuantity() > 0) {
      orderProduct.setQuantity(orderProduct.getQuantity()-1);
      product.setQuantity(product.getQuantity()+1);
      productRepository.save(product);
      orderProduct.setProduct(product);
      orderProductRepository.save(orderProduct);
      return ResponseEntity.ok("Order quantity update successfully !");
  } else {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not enough product quantity to add");
  }

  }

  /**
   * Delete orderProduct
   * 
   * @param orderProductId The userId who owns the order
   * @return the ResponseEntity with status 200 (ok) and with body "Order quantity deleted successfully !"
   */
  @DeleteMapping("/deleteOrderproduct/{orderProductId}")
  public ResponseEntity<String> deleteOrderProduct(@PathVariable Long orderProductId) {
    iOrderProductService.deleteOrderProduct(orderProductId);
    return ResponseEntity.ok("Order product deleted successfully !");
  }

}
