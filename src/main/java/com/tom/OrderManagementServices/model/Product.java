package com.tom.OrderManagementServices.model;

import jakarta.persistence.*;
import java.sql.Date;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Entity
@Table(name = "Products")
@EnableJpaAuditing(setDates = true)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long ProductId;

  @Column(nullable = false)
  private String ProductName;

  @Column(nullable = false)
  private String ProductDescripition;

  @Column(nullable = false)
  private String Category;

  @Column(nullable = false)
  private String Color;

  @Column(nullable = false)
  private String Picture;

  @Column(nullable = false)
  private String Ranking;

  @Column(nullable = false)
  private String UnitPriceCurrency;

  @Column(nullable = false)
  private String Price;

  @Column(nullable = false)
  private String Validity;

  @Column(nullable = false)
  private String Data;

  @Column(nullable = false)
  private String Speed;

  @Column(nullable = false)
  private String Voice;

  @Column(nullable = false)
  private String SMS;

  @Column(nullable = false)
  private String TVSubscriptions;

  @Column(nullable = false)
  private boolean isActive;

  @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
  private Date createDate;

  @Column(name = "isActive")
  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public long getProductId() {
    return ProductId;
  }

  public void setProductId(long productId) {
    ProductId = productId;
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

  public String getColor() {
    return Color;
  }

  public void setColor(String color) {
    Color = color;
  }

  public String getPicture() {
    return Picture;
  }

  public void setPicture(String picture) {
    Picture = picture;
  }

  public String getRanking() {
    return Ranking;
  }

  public void setRanking(String ranking) {
    Ranking = ranking;
  }

  public String getUnitPriceCurrency() {
    return UnitPriceCurrency;
  }

  public void setUnitPriceCurrency(String unitPriceCurrency) {
    UnitPriceCurrency = unitPriceCurrency;
  }

  public String getCategory() {
    return Category;
  }

  public void setCategory(String category) {
    Category = category;
  }

  public String getPrice() {
    return Price;
  }

  public void setPrice(String price) {
    Price = price;
  }

  public String getValidity() {
    return Validity;
  }

  public void setValidity(String validity) {
    Validity = validity;
  }

  public String getData() {
    return Data;
  }

  public void setData(String data) {
    Data = data;
  }

  public String getSpeed() {
    return Speed;
  }

  public void setSpeed(String speed) {
    Speed = speed;
  }

  public String getVoice() {
    return Voice;
  }

  public void setVoice(String voice) {
    Voice = voice;
  }

  public String getSMS() {
    return SMS;
  }

  public void setSMS(String sMS) {
    SMS = sMS;
  }

  public String getTVSubscriptions() {
    return TVSubscriptions;
  }

  public void setTVSubscriptions(String tVSubscriptions) {
    TVSubscriptions = tVSubscriptions;
  }
}
