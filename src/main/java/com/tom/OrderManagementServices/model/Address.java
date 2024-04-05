package com.tom.OrderManagementServices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Address")
public class Address {

  private static final boolean True = false;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long AddressId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "customer_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Customer customer;

  @JsonProperty
  @Column(nullable = false, length = 30)
  private String Street1;

  @JsonProperty
  @Column(nullable = false, length = 30)
  private String Street2;

  @Column(nullable = false, length = 30)
  private String City;

  @JsonProperty
  @Column(nullable = false, length = 40)
  private String State;

  @JsonProperty
  @Column(nullable = false, length = 40)
  private String Country;

  @JsonProperty
  @Column(nullable = true, length = 30)
  private String Pobox;

  @JsonProperty
  @Column(nullable = false, length = 10)
  private String PostalCode;

  @JsonProperty
  @Column(nullable = true, length = 10)
  private String AddressType;

  public String getStreet1() {

    return Street1;
  }

  public void setStreet1(String street1) {

    Street1 = street1;
  }

  public String getStreet2() {

    return Street2;
  }

  public void setStreet2(String street2) {

    Street2 = street2;
  }

  public String getCity() {

    return City;
  }

  public void setCity(String city) {

    City = city;
  }

  public String getState() {

    return State;
  }

  public void setState(String state) {

    State = state;
  }

  public String getCountry() {

    return Country;
  }

  public void setCountry(String country) {

    Country = country;
  }

  public String getPobox() {

    return Pobox;
  }

  public void setPobox(String pobox) {

    Pobox = pobox;
  }

  public String getPostalCode() {

    return PostalCode;
  }

  public void setPostalCode(String postalCode) {

    PostalCode = postalCode;
  }

  public String getAddressType() {

    return AddressType;
  }

  public void setAddressType(String addressType) {

    AddressType = addressType;
  }

  public long getAddressId() {
    return AddressId;
  }

  public void setAddressId(long addressId) {
    AddressId = addressId;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Address(
      long addressId,
      Customer customer,
      String street1,
      String street2,
      String city,
      String state,
      String country,
      String pobox,
      String postalCode,
      String addressType) {
    super();
    AddressId = addressId;
    this.customer = customer;
    Street1 = street1;
    Street2 = street2;
    City = city;
    State = state;
    Country = country;
    Pobox = pobox;
    PostalCode = postalCode;
    AddressType = addressType;
  }

  public Address() {
    super();
  }

  @Override
  public String toString() {
    return "Address [AddressId="
        + AddressId
        + ", customer="
        + customer
        + ", Street1="
        + Street1
        + ", Street2="
        + Street2
        + ", City="
        + City
        + ", State="
        + State
        + ", Country="
        + Country
        + ", Pobox="
        + Pobox
        + ", PostalCode="
        + PostalCode
        + ", AddressType="
        + AddressType
        + "]";
  }
}
