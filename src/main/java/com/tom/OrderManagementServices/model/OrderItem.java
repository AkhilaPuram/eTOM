package com.tom.OrderManagementServices.model;

import jakarta.persistence.*;

@Entity
@Table(name = "OrderItems")
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
  private Long id;

  @Column(nullable = true, length = 15)
  private int OrderLineID;

  @Column(nullable = false, length = 16)
  private int Quantity;

  @Column(nullable = true, length = 40)
  private long ProductID;

  @Column(nullable = false, length = 16)
  private float TaxAmount;

  @Column(nullable = true, length = 10)
  private String TaxCurrency;

  @Column(nullable = true, length = 16)
  private float ShippingAmount;

  @Column(nullable = true, length = 16)
  private String ShippingCurrency;

  @Column(nullable = true, length = 16)
  private float UnitPriceAmount;

  @Column(nullable = true, length = 16)
  private String UnitPriceCurrency;

  @Column(nullable = true, length = 16)
  private float MSRPAmount;

  @Column(nullable = true, length = 10)
  private String MSRPCurrency;

  @Column(nullable = true, length = 255)
  private String Description;

  @Column(nullable = true, length = 255)
  private String serviceType;

  @Lob private String content;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "order_id")
  private Order order;

  public Long getId() {
    return id;
  }

  public int getOrderLineID() {
    return OrderLineID;
  }

  public void setOrderLineID(int orderLineID) {
    OrderLineID = orderLineID;
  }

  public int getQuantity() {
    return Quantity;
  }

  public void setQuantity(int quantity) {
    Quantity = quantity;
  }

  public long getProductID() {
    return ProductID;
  }

  public void setProductID(long productID) {
    ProductID = productID;
  }

  public float getTaxAmount() {
    return TaxAmount;
  }

  public void setTaxAmount(float taxAmount) {
    TaxAmount = taxAmount;
  }

  public String getTaxCurrency() {
    return TaxCurrency;
  }

  public void setTaxCurrency(String taxCurrency) {
    TaxCurrency = taxCurrency;
  }

  public float getShippingAmount() {
    return ShippingAmount;
  }

  public void setShippingAmount(float shippingAmount) {
    ShippingAmount = shippingAmount;
  }

  public String getShippingCurrency() {
    return ShippingCurrency;
  }

  public void setShippingCurrency(String shippingCurrency) {
    ShippingCurrency = shippingCurrency;
  }

  public float getUnitPriceAmount() {
    return UnitPriceAmount;
  }

  public void setUnitPriceAmount(float unitPriceAmount) {
    UnitPriceAmount = unitPriceAmount;
  }

  public String getUnitPriceCurrency() {
    return UnitPriceCurrency;
  }

  public void setUnitPriceCurrency(String unitPriceCurrency) {
    UnitPriceCurrency = unitPriceCurrency;
  }

  public float getMSRPAmount() {
    return MSRPAmount;
  }

  public void setMSRPAmount(float mSRPAmount) {
    MSRPAmount = mSRPAmount;
  }

  public String getMSRPCurrency() {
    return MSRPCurrency;
  }

  public void setMSRPCurrency(String mSRPCurrency) {
    MSRPCurrency = mSRPCurrency;
  }

  public String getDescription() {
    return Description;
  }

  public String getServiceType() {
    return serviceType;
  }

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public void setDescription(String description) {
    Description = description;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "OrderItem [id="
        + id
        + ", OrderLineID="
        + OrderLineID
        + ", Quantity="
        + Quantity
        + ", ProductID="
        + ProductID
        + ", TaxAmount="
        + TaxAmount
        + ", TaxCurrency="
        + TaxCurrency
        + ", ShippingAmount="
        + ShippingAmount
        + ", ShippingCurrency="
        + ShippingCurrency
        + ", UnitPriceAmount="
        + UnitPriceAmount
        + ", UnitPriceCurrency="
        + UnitPriceCurrency
        + ", MSRPAmount="
        + MSRPAmount
        + ", MSRPCurrency="
        + MSRPCurrency
        + ", Description="
        + Description
        + ", serviceType="
        + serviceType
        + ", content="
        + content
        + ", order="
        + order
        + "]";
  }

  public OrderItem(
      Long id,
      int orderLineID,
      int quantity,
      long productID,
      float taxAmount,
      String taxCurrency,
      float shippingAmount,
      String shippingCurrency,
      float unitPriceAmount,
      String unitPriceCurrency,
      float mSRPAmount,
      String mSRPCurrency,
      String description,
      String serviceType,
      String content,
      Order order) {
    super();
    this.id = id;
    OrderLineID = orderLineID;
    Quantity = quantity;
    ProductID = productID;
    TaxAmount = taxAmount;
    TaxCurrency = taxCurrency;
    ShippingAmount = shippingAmount;
    ShippingCurrency = shippingCurrency;
    UnitPriceAmount = unitPriceAmount;
    UnitPriceCurrency = unitPriceCurrency;
    MSRPAmount = mSRPAmount;
    MSRPCurrency = mSRPCurrency;
    Description = description;
    this.serviceType = serviceType;
    this.content = content;
    this.order = order;
  }

  public OrderItem() {
    super();
    // TODO Auto-generated constructor stub
  }
}
