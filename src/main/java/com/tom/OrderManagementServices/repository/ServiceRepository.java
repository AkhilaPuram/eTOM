package com.tom.OrderManagementServices.repository;

import com.tom.OrderManagementServices.model.ValidateService;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ValidateService, Long> {

  List<ValidateService> findByPostalCode(String postalCode);
  // TODO Auto-generated method stub

}
