package com.tom.OrderManagementServices.controller;

import com.tom.OrderManagementServices.dto.CustomerOrders;
import com.tom.OrderManagementServices.repository.CustomerRepository;
import com.tom.OrderManagementServices.service.OrderProcess;
import com.tom.OrderManagementServices.service.OrderService;
import io.camunda.zeebe.client.ZeebeClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/etom/")
public class CustomerOrderController {
  @Autowired private ZeebeClient client;
  @Autowired private OrderProcess OP;
  @Autowired OrderService orderService;
  @Autowired CustomerRepository customerRepository;

  @GetMapping("/myorders/{emailid}")
  public List<CustomerOrders> getCustomerOrders(@PathVariable("emailid") String emailID) {

    System.out.println("---email ID-----" + emailID);
    List<CustomerOrders> customerOrders = orderService.getCustomerOrder(emailID);
    return customerOrders;
  }
}
