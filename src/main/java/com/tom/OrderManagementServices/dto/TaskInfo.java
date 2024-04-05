package com.tom.OrderManagementServices.dto;

import com.tom.OrderManagementServices.model.*;
import java.util.List;

public class TaskInfo {

  private Customer customer;

  private Order order;

  private List<OrderItem> orderitem;

  private List<Address> address;

  private String orderID;

  private String customerID;

  public String getOrderID() {
    return orderID;
  }

  public void setOrderID(String orderID) {
    this.orderID = orderID;
  }

  public String getCustomerID() {
    return customerID;
  }

  public void setCustomerID(String customerID) {
    this.customerID = customerID;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public List<OrderItem> getOrderitem() {
    return orderitem;
  }

  public void setOrderitem(List<OrderItem> orderitem) {
    this.orderitem = orderitem;
  }

  public List<Address> getAddress() {
    return address;
  }

  public void setAddress(List<Address> address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "CreateOrderRequest [customer="
        + customer
        + ", order="
        + order
        + ", orderitem="
        + orderitem
        + ", address="
        + address
        + "]";
  }
}
