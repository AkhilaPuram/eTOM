package com.tom.OrderManagementServices.repository;

import com.tom.OrderManagementServices.model.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  List<Customer> findByEmailID(String emailID);
}
