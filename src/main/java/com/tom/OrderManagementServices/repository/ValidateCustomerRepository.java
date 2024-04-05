package com.tom.OrderManagementServices.repository;

import com.tom.OrderManagementServices.model.ValidateCustomer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidateCustomerRepository extends JpaRepository<ValidateCustomer, Long> {

  List<ValidateCustomer> findByCustomerID(Long postId);
}
