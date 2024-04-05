package com.tom.OrderManagementServices.dto;

import com.tom.OrderManagementServices.model.*;
import java.util.List;

public class CreateOrderRequest {

  private Customer customer;

  private Order order;

  private List<OrderItem> orderitem;

  private List<Address> address;

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
