package com.tom.OrderManagementServices;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableZeebeClient
@SpringBootApplication
@Deployment(resources = "classpath*:/model/*.*")
public class OrderManagementServicesApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrderManagementServicesApplication.class, args);
  }
}
