package com.tom.OrderManagementServices.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CustomerCredibility")
public class CustomerCredibility {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long ID;

  private long customerID;

  private String emailID;

  public String getEmailID() {
    return emailID;
  }

  public void setEmailID(String emailID) {
    this.emailID = emailID;
  }

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

  public CustomerCredibility() {
    super();
  }

  public static Object ValidateCustomer(Object validatecustomerrepository_repository) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String toString() {
    return "CustomerCredibility [ID="
        + ID
        + ", customerID="
        + customerID
        + ", emailID="
        + emailID
        + ", Creditaibility="
        + Creditaibility
        + ", isActive="
        + isActive
        + "]";
  }

  public CustomerCredibility(
      long iD, long customerID, String emailID, String creditaibility, String isActive) {
    super();
    ID = iD;
    this.customerID = customerID;
    this.emailID = emailID;
    Creditaibility = creditaibility;
    this.isActive = isActive;
  }
}
