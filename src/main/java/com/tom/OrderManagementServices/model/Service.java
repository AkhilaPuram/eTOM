package com.tom.OrderManagementServices.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Service")
public class Service {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long Id;

  @Column(nullable = false)
  private String AccountId;

  @Column(nullable = false)
  private String AccountType;

  @Column(nullable = false)
  private String CustomerId;
}
