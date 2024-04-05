package com.tom.OrderManagementServices.service;

import com.tom.OrderManagementServices.model.ValidateCustomer;
import com.tom.OrderManagementServices.repository.CustomerCredibilityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCredService {
  @Autowired public CustomerCredibilityRepository customerCredibilityRep;

  // @Autowired public ProductRepository test;

  // ProductRepository test1 = new ProductRepository();

  public String validateCustomerCredibility(String customerID) {

    List<ValidateCustomer> isvalidCustomer = customerCredibilityRep.findByEmailID(customerID);

    String isInValidCustomer = "false";

    if (!isvalidCustomer.equals(null)) {
      System.out.println("------------customerCredibility--------" + isvalidCustomer.size());
      isInValidCustomer = isvalidCustomer.get(0).getCreditaibility();
    }

    return isInValidCustomer;
  }
}
