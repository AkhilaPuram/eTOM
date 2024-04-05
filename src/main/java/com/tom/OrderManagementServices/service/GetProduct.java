package com.tom.OrderManagementServices.service;

import com.tom.OrderManagementServices.model.Product;
import com.tom.OrderManagementServices.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class GetProduct {
  @Autowired public ProductRepository product_repository;

  // @Autowired public ProductRepository test;

  // ProductRepository test1 = new ProductRepository();

  public List<Product> getProducts(ProductRepository product_repository) {
    System.out.println(product_repository);
    // System.out.println(test);
    List<Product> products = new ArrayList<Product>();
    product_repository.findAll().forEach(Product -> products.add(Product));
    return products;
  }
}
