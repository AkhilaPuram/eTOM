package com.tom.OrderManagementServices.repository;

import com.tom.OrderManagementServices.model.CustomerCredibility;
import com.tom.OrderManagementServices.model.ValidateCustomer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCredibilityRepository extends JpaRepository<CustomerCredibility, Long> {

  List<ValidateCustomer> findByEmailID(String emailID);
}
