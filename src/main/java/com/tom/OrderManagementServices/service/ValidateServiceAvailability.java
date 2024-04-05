package com.tom.OrderManagementServices.service;

import com.tom.OrderManagementServices.model.ValidateService;
import com.tom.OrderManagementServices.repository.ServiceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateServiceAvailability {
  @Autowired public ServiceRepository serviceRepository;

  // @Autowired public ProductRepository test;

  // ProductRepository test1 = new ProductRepository();

  public String validateAvailability(String postalcode) {

    List<ValidateService> isvalidService = serviceRepository.findByPostalCode(postalcode);

    String serviceAvl = "false";

    if (!isvalidService.equals(null)) {
      System.out.println("------------check service availability--------" + isvalidService.size());

      serviceAvl = isvalidService.get(0).getAvailability();
    }

    return serviceAvl;
  }
}
