package com.tom.OrderManagementServices.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ValidateCustomer")
public class ValidateCustomer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long ID;

  private long customerID;

  public long getCustomerID() {
    return customerID;
  }

  public void setCustomerID(long customerID) {
    customerID = customerID;
  }

  @Column(nullable = false)
  private String Creditaibility;

  @Column(nullable = false)
  private String isActive;

  public long getID() {
    return ID;
  }

  public void setID(long iD) {
    ID = iD;
  }

  public String getCreditaibility() {
    return Creditaibility;
  }

  public void setCreditaibility(String creditaibility) {
    Creditaibility = creditaibility;
  }

  public String getIsActive() {
    return isActive;
  }

  public void setIsActive(String isActive) {
    this.isActive = isActive;
  }

  public ValidateCustomer(long iD, String customerID, String creditaibility, String isActive) {
    super();
    ID = iD;
    customerID = customerID;
    Creditaibility = creditaibility;
    this.isActive = isActive;
  }

  @Override
  public String toString() {
    return "ValidateCustomer [ID="
        + ID
        + ", CustomerID="
        + customerID
        + ", Creditaibility="
        + Creditaibility
        + ", isActive="
        + isActive
        + "]";
  }

  public ValidateCustomer() {
    super();
  }

  public static Object ValidateCustomer(Object validatecustomerrepository_repository) {
    // TODO Auto-generated method stub
    return null;
  }
}
