package com.tom.OrderManagementServices.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Billings")
public class Billing {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long BillingId;

  @Column(nullable = false)
  private String AccountId;

  @Column(nullable = false)
  private String AccountType;

  @Column(nullable = false)
  private String CustomerId;

  @Column(nullable = false)
  private String OutstandingAmount;

  @Column(nullable = false)
  private String Last3PaymentsDetails;

  @Column(nullable = false)
  private String BillingStartDate;

  @Column(nullable = false)
  private String BillingEndDate;

  @Column(nullable = false)
  private String CurrentBillPlan;

  @Column(nullable = false)
  private String BillingCycle;

  @Column(nullable = false)
  private String ServiceRequestStatus;

  @Column(nullable = false)
  private String NewBillPlans;

  @Column(nullable = false)
  private String EmailBillSubscription;

  @Column(nullable = false)
  private String BillSummary;

  public long getBillingId() {
    return BillingId;
  }

  public void setBillingId(long billingId) {
    BillingId = billingId;
  }

  public String getAccountId() {
    return AccountId;
  }

  public void setAccountId(String accountId) {
    AccountId = accountId;
  }

  public String getAccountType() {
    return AccountType;
  }

  public void setAccountType(String accountType) {
    AccountType = accountType;
  }

  public String getCustomerId() {
    return CustomerId;
  }

  public void setCustomerId(String customerId) {
    CustomerId = customerId;
  }

  public String getOutstandingAmount() {
    return OutstandingAmount;
  }

  public void setOutstandingAmount(String outstandingAmount) {
    OutstandingAmount = outstandingAmount;
  }

  public String getLast3PaymentsDetails() {
    return Last3PaymentsDetails;
  }

  public void setLast3PaymentsDetails(String last3PaymentsDetails) {
    Last3PaymentsDetails = last3PaymentsDetails;
  }

  public String getBillingStartDate() {
    return BillingStartDate;
  }

  public void setBillingStartDate(String billingStartDate) {
    BillingStartDate = billingStartDate;
  }

  public String getBillingEndDate() {
    return BillingEndDate;
  }

  public void setBillingEndDate(String billingEndDate) {
    BillingEndDate = billingEndDate;
  }

  public String getCurrentBillPlan() {
    return CurrentBillPlan;
  }

  public void setCurrentBillPlan(String currentBillPlan) {
    CurrentBillPlan = currentBillPlan;
  }

  public String getBillingCycle() {
    return BillingCycle;
  }

  public void setBillingCycle(String billingCycle) {
    BillingCycle = billingCycle;
  }

  public String getServiceRequestStatus() {
    return ServiceRequestStatus;
  }

  public void setServiceRequestStatus(String serviceRequestStatus) {
    ServiceRequestStatus = serviceRequestStatus;
  }

  public String getNewBillPlans() {
    return NewBillPlans;
  }

  public void setNewBillPlans(String newBillPlans) {
    NewBillPlans = newBillPlans;
  }

  public String getEmailBillSubscription() {
    return EmailBillSubscription;
  }

  public void setEmailBillSubscription(String emailBillSubscription) {
    EmailBillSubscription = emailBillSubscription;
  }

  public String getBillSummary() {
    return BillSummary;
  }

  public void setBillSummary(String billSummary) {
    BillSummary = billSummary;
  }

  public Billing(
      long billingId,
      String accountId,
      String accountType,
      String customerId,
      String outstandingAmount,
      String last3PaymentsDetails,
      String billingStartDate,
      String billingEndDate,
      String currentBillPlan,
      String billingCycle,
      String serviceRequestStatus,
      String newBillPlans,
      String emailBillSubscription,
      String billSummary) {
    super();
    BillingId = billingId;
    AccountId = accountId;
    AccountType = accountType;
    CustomerId = customerId;
    OutstandingAmount = outstandingAmount;
    Last3PaymentsDetails = last3PaymentsDetails;
    BillingStartDate = billingStartDate;
    BillingEndDate = billingEndDate;
    CurrentBillPlan = currentBillPlan;
    BillingCycle = billingCycle;
    ServiceRequestStatus = serviceRequestStatus;
    NewBillPlans = newBillPlans;
    EmailBillSubscription = emailBillSubscription;
    BillSummary = billSummary;
  }

  public Billing() {
    super();
  }

  @Override
  public String toString() {
    return "Billing [BillingId="
        + BillingId
        + ", AccountId="
        + AccountId
        + ", AccountType="
        + AccountType
        + ", CustomerId="
        + CustomerId
        + ", OutstandingAmount="
        + OutstandingAmount
        + ", Last3PaymentsDetails="
        + Last3PaymentsDetails
        + ", BillingStartDate="
        + BillingStartDate
        + ", BillingEndDate="
        + BillingEndDate
        + ", CurrentBillPlan="
        + CurrentBillPlan
        + ", BillingCycle="
        + BillingCycle
        + ", ServiceRequestStatus="
        + ServiceRequestStatus
        + ", NewBillPlans="
        + NewBillPlans
        + ", EmailBillSubscription="
        + EmailBillSubscription
        + ", BillSummary="
        + BillSummary
        + "]";
  }
}
