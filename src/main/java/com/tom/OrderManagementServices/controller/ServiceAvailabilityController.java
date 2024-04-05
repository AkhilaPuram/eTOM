package com.tom.OrderManagementServices.controller;

import com.tom.OrderManagementServices.service.OrderProcess;
import com.tom.OrderManagementServices.service.ValidateServiceAvailability;
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
public class ServiceAvailabilityController {

  @Autowired private ZeebeClient zeebe;
  @Autowired private OrderProcess OP;
  @Autowired ValidateServiceAvailability validateServiceAvailability;

  @GetMapping("/serviceavailability/{postalcode}")
  public ResponseEntity<Object> validateServiceAvailability(@PathVariable String postalcode) {

    System.out.println("-----Postalcode ----" + postalcode);
    HttpHeaders responseheaders = new HttpHeaders();

    responseheaders.setContentType(MediaType.APPLICATION_JSON);

    String Availability = validateServiceAvailability.validateAvailability(postalcode);

    return ResponseEntity.status(HttpStatus.OK).headers(responseheaders).body(Availability);
  }
}
