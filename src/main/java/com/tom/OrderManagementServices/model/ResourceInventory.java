package com.tom.OrderManagementServices.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Entity
@Table(name = "ResourceInventory")
@EnableJpaAuditing(setDates = true)
public class ResourceInventory {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false)
  private String ProductName;

  @Column(nullable = false)
  private String ProductDescripition;

  @Column(nullable = false)
  private int noOfItemsAvailable;

  @Column(nullable = true, length = 40)
  private long productID;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getProductName() {
    return ProductName;
  }

  public void setProductName(String productName) {
    ProductName = productName;
  }

  public String getProductDescripition() {
    return ProductDescripition;
  }

  public void setProductDescripition(String productDescripition) {
    ProductDescripition = productDescripition;
  }

  public int getNoOfItemsAvailable() {
    return noOfItemsAvailable;
  }

  public void setNoOfItemsAvailable(int noOfItemsAvailable) {
    this.noOfItemsAvailable = noOfItemsAvailable;
  }

  public long getProductID() {
    return productID;
  }

  public void setProductID(long productID) {
    this.productID = productID;
  }

  @Override
  public String toString() {
    return "ResourceInventory [id="
        + id
        + ", ProductName="
        + ProductName
        + ", ProductDescripition="
        + ProductDescripition
        + ", noOfItemsAvailable="
        + noOfItemsAvailable
        + ", productID="
        + productID
        + "]";
  }

  public ResourceInventory(
      long id,
      String productName,
      String productDescripition,
      int noOfItemsAvailable,
      long productID) {
    super();
    this.id = id;
    ProductName = productName;
    ProductDescripition = productDescripition;
    this.noOfItemsAvailable = noOfItemsAvailable;
    this.productID = productID;
  }

  public ResourceInventory() {
    super();
    // TODO Auto-generated constructor stub
  }
}
