package com.tom.OrderManagementServices.repository;

import com.tom.OrderManagementServices.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {}
