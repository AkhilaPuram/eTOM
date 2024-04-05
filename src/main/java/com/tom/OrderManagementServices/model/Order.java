package com.tom.OrderManagementServices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = true, length = 20)
  private String Status;

  @Column(nullable = true, length = 20)
  private java.sql.Date OrderDate;

  @Column(nullable = true, length = 40)
  private String ShippingMethod;

  @Column(nullable = false, length = 16)
  private float ShippingAmount;

  @Column(nullable = false, length = 16)
  private float PriceAmount;

  @Column(nullable = true, length = 20)
  private String ShippingAddress;

  @Column(nullable = false, length = 254)
  private String SpecialInstructions;

  @Column(nullable = true, length = 254)
  private int SplittingPreference;

  @Column(nullable = true, length = 16)
  private float OrdersSubtotal;

  @JsonProperty
  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "CustomerID")
  private Customer customer;

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStatus() {
    return Status;
  }

  public void setStatus(String status) {
    Status = status;
  }

  public java.sql.Date getOrderDate() {
    return OrderDate;
  }

  public void setOrderDate(java.sql.Date orderDate) {
    OrderDate = orderDate;
  }

  public String getShippingMethod() {
    return ShippingMethod;
  }

  public void setShippingMethod(String shippingMethod) {
    ShippingMethod = shippingMethod;
  }

  public float getShippingAmount() {
    return ShippingAmount;
  }

  public void setShippingAmount(float shippingAmount) {
    ShippingAmount = shippingAmount;
  }

  public float getPriceAmount() {
    return PriceAmount;
  }

  public void setPriceAmount(float priceAmount) {
    PriceAmount = priceAmount;
  }

  public String getShippingAddress() {
    return ShippingAddress;
  }

  public void setShippingAddress(String shippingAddress) {
    ShippingAddress = shippingAddress;
  }

  public String getSpecialInstructions() {
    return SpecialInstructions;
  }

  public void setSpecialInstructions(String specialInstructions) {
    SpecialInstructions = specialInstructions;
  }

  public int getSplittingPreference() {
    return SplittingPreference;
  }

  public void setSplittingPreference(int splittingPreference) {
    SplittingPreference = splittingPreference;
  }

  public float getOrdersSubtotal() {
    return OrdersSubtotal;
  }

  public void setOrdersSubtotal(float ordersSubtotal) {
    OrdersSubtotal = ordersSubtotal;
  }

  @Override
  public String toString() {
    return "Order [id="
        + id
        + ", Status="
        + Status
        + ", OrderDate="
        + OrderDate
        + ", ShippingMethod="
        + ShippingMethod
        + ", ShippingAmount="
        + ShippingAmount
        + ", PriceAmount="
        + PriceAmount
        + ", ShippingAddress="
        + ShippingAddress
        + ", SpecialInstructions="
        + SpecialInstructions
        + ", SplittingPreference="
        + SplittingPreference
        + ", OrdersSubtotal="
        + OrdersSubtotal
        + ", customer="
        + customer
        + "]";
  }

  public Order(
      Long id,
      String status,
      Date orderDate,
      String shippingMethod,
      float shippingAmount,
      float priceAmount,
      String shippingAddress,
      String specialInstructions,
      int splittingPreference,
      float ordersSubtotal,
      Customer customer) {
    super();
    this.id = id;
    Status = status;
    OrderDate = orderDate;
    ShippingMethod = shippingMethod;
    ShippingAmount = shippingAmount;
    PriceAmount = priceAmount;
    ShippingAddress = shippingAddress;
    SpecialInstructions = specialInstructions;
    SplittingPreference = splittingPreference;
    OrdersSubtotal = ordersSubtotal;
    this.customer = customer;
  }

  public Order() {
    super();
  }
}
