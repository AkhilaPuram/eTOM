package com.tom.OrderManagementServices.controller;

import com.tom.OrderManagementServices.service.CustomerCredService;
import com.tom.OrderManagementServices.service.OrderProcess;
import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CustomerCredibilityController {

  @Autowired private ZeebeClient zeebe;
  @Autowired private OrderProcess OP;
  @Autowired CustomerCredService customerCredService;

  @GetMapping("/validatecustomer/{customerId}")
  public ResponseEntity<Object> validateCustomer(@PathVariable String customerId) {

    System.out.println("-----customerID ----" + customerId);
    HttpHeaders responseheaders = new HttpHeaders();

    responseheaders.setContentType(MediaType.APPLICATION_JSON);

    String customerCredibility = customerCredService.validateCustomerCredibility(customerId);

    return ResponseEntity.status(HttpStatus.OK).headers(responseheaders).body(customerCredibility);
  }
}
