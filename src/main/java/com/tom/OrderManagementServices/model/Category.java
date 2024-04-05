package com.tom.OrderManagementServices.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Categories")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long CategoryId;

  @Column(nullable = false)
  private String CategoryName;

  @Column(nullable = false)
  private String Description;

  public long getCategoryId() {
    return CategoryId;
  }

  public void setCategoryId(long categoryId) {
    CategoryId = categoryId;
  }

  public String getCategoryName() {
    return CategoryName;
  }

  public void setCategoryName(String categoryName) {
    CategoryName = categoryName;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }

  @Override
  public String toString() {
    return "Category [CategoryId="
        + CategoryId
        + ", CategoryName="
        + CategoryName
        + ", Description="
        + Description
        + "]";
  }

  public Category(long categoryId, String categoryName, String description) {
    super();
    CategoryId = categoryId;
    CategoryName = categoryName;
    Description = description;
  }

  public Category() {
    super();
  }
}
