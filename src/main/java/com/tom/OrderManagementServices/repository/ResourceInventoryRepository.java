package com.tom.OrderManagementServices.repository;

import com.tom.OrderManagementServices.model.ResourceInventory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceInventoryRepository extends JpaRepository<ResourceInventory, Long> {

  List<ResourceInventory> findByProductID(long productID);
}
