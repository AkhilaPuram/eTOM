package com.tom.OrderManagementServices.repository;

import com.tom.OrderManagementServices.model.OrderItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

  // List<OrderItem> findbyOrderID(Long orderID);

  List<OrderItem> findByOrderId(Long postId);
}
