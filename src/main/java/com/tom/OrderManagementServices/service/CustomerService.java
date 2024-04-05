package com.tom.OrderManagementServices.service;

import com.tom.OrderManagementServices.model.Customer;
import com.tom.OrderManagementServices.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired CustomerRepository customerRepository;

  public Customer createCustomer(Customer customer) {

    customerRepository.save(customer);
    return customer;
  }

  public Customer getCustomer(String customerID) {

    Customer customer = customerRepository.findById(Long.parseLong(customerID)).get();
    return customer;
  }
}
