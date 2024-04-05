package com.tom.OrderManagementServices.repository;

import com.tom.OrderManagementServices.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> findByCustomerId(Long postId);
}
