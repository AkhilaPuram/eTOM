package com.tom.OrderManagementServices.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Customers")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = true)
  private String CustomrType;

  @Column(nullable = false)
  private String FirstName;

  @Column(nullable = false)
  private String LastName;

  @Column(nullable = false)
  private String MiddleName;

  @Column(nullable = false)
  private String Title;

  @Column(nullable = true)
  private String Suffix;

  @Column(nullable = false, length = 20)
  private String HomePhone;

  @Column(nullable = true, length = 20)
  private String BusinessPhone;

  @Column(nullable = false, length = 15)
  private String Fax;

  @Column(nullable = true, length = 20)
  private String emailID;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCustomrType() {
    return CustomrType;
  }

  public void setCustomrType(String customrType) {
    CustomrType = customrType;
  }

  public String getFirstName() {
    return FirstName;
  }

  public void setFirstName(String firstName) {
    FirstName = firstName;
  }

  public String getLastName() {
    return LastName;
  }

  public void setLastName(String lastName) {
    LastName = lastName;
  }

  public String getMiddleName() {
    return MiddleName;
  }

  public void setMiddleName(String middleName) {
    MiddleName = middleName;
  }

  public String getTitle() {
    return Title;
  }

  public void setTitle(String title) {
    Title = title;
  }

  public String getSuffix() {
    return Suffix;
  }

  public void setSuffix(String suffix) {
    Suffix = suffix;
  }

  public String getHomePhone() {
    return HomePhone;
  }

  public void setHomePhone(String homePhone) {
    HomePhone = homePhone;
  }

  public String getBusinessPhone() {
    return BusinessPhone;
  }

  public void setBusinessPhone(String businessPhone) {
    BusinessPhone = businessPhone;
  }

  public String getFax() {
    return Fax;
  }

  public void setFax(String fax) {
    Fax = fax;
  }

  public String getEmailID() {
    return emailID;
  }

  public void setEmailID(String emailID) {
    this.emailID = emailID;
  }

  public Customer() {
    super();
  }

  public Customer(
      long id,
      String customrType,
      String firstName,
      String lastName,
      String middleName,
      String title,
      String suffix,
      String homePhone,
      String businessPhone,
      String fax,
      String emailID) {
    super();
    this.id = id;
    CustomrType = customrType;
    FirstName = firstName;
    LastName = lastName;
    MiddleName = middleName;
    Title = title;
    Suffix = suffix;
    HomePhone = homePhone;
    BusinessPhone = businessPhone;
    Fax = fax;
    this.emailID = emailID;
  }

  @Override
  public String toString() {
    return "Customer [id="
        + id
        + ", CustomrType="
        + CustomrType
        + ", FirstName="
        + FirstName
        + ", LastName="
        + LastName
        + ", MiddleName="
        + MiddleName
        + ", Title="
        + Title
        + ", Suffix="
        + Suffix
        + ", HomePhone="
        + HomePhone
        + ", BusinessPhone="
        + BusinessPhone
        + ", Fax="
        + Fax
        + ", emailID="
        + emailID
        + "]";
  }
}
