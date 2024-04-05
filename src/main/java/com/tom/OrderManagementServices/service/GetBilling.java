package com.tom.OrderManagementServices.service;

import com.tom.OrderManagementServices.model.Billing;
import com.tom.OrderManagementServices.repository.BillingRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class GetBilling {
  @Autowired public BillingRepository billing_repository;

  // @Autowired public BillingRepository test;

  // BillingRepository test1 = new BillingRepository();

  public List<Billing> getBillings(BillingRepository billing_repository) {
    System.out.println(billing_repository);
    // System.out.println(test);
    List<Billing> billings = new ArrayList<Billing>();
    billing_repository.findAll().forEach(Billing -> billings.add(Billing));
    return billings;
  }
}
