package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {

  List<Order> findAllByOrderByOrderIdDesc();

  @Modifying
  @Query(
      "UPDATE Order o SET o.orderStatus = :status, o.lastUpdateTime = CURRENT_TIMESTAMP"
          + " WHERE o.orderId = :orderId")
  void updateOrderStatus(int orderId, int status);
}
