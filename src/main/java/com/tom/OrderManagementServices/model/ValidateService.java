package com.tom.OrderManagementServices.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ValidateService")
public class ValidateService {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long Id;

  @Column(nullable = false, length = 30)
  private String postalCode;

  @Column(nullable = false, length = 30)
  private String Plan;

  @Column(nullable = false, length = 30)
  private String Availability;

  public long getId() {
    return Id;
  }

  public void setId(long id) {
    Id = id;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    postalCode = postalCode;
  }

  public String getPlan() {
    return Plan;
  }

  public void setPlan(String plan) {
    Plan = plan;
  }

  public String getAvailability() {
    return Availability;
  }

  public void setAvailability(String availability) {
    Availability = availability;
  }

  public ValidateService(long id, String postalCode, String plan, String availability) {
    super();
    Id = id;
    postalCode = postalCode;
    Plan = plan;
    Availability = availability;
  }

  @Override
  public String toString() {
    return "ValidateService [Id="
        + Id
        + ", PostalCode="
        + postalCode
        + ", Plan="
        + Plan
        + ", Availability="
        + Availability
        + ", getId()="
        + getId()
        + ", getPostalCode()="
        + getPostalCode()
        + ", getPlan()="
        + getPlan()
        + ", getAvailability()="
        + getAvailability()
        + ", getClass()="
        + getClass()
        + ", hashCode()="
        + hashCode()
        + ", toString()="
        + super.toString()
        + "]";
  }

  public ValidateService() {
    super();
    // TODO Auto-generated constructor stub
  }
}
