package com.tom.OrderManagementServices.worker;

import com.tom.OrderManagementServices.service.EmailService;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class TestWorker {
  //
  //  @JobWorker(type = "OrderFeasibilityCheck")
  //  public void OrderFeasibilityCheck1(final JobClient client, final ActivatedJob job) {
  //    String isCustomerValidValue = "";
  //    System.out.println("Inside Order Feasibility Check" + job.getVariables());
  //
  //    Map<String, String> map = new HashMap<String, String>();
  //    Map<String, Object> variables = job.getVariablesAsMap();
  //
  //    for (Map.Entry<String, Object> entry : variables.entrySet()) {
  //      if (entry.getKey() == "isCustomerValid") {
  //        isCustomerValidValue = (String) entry.getValue();
  //        System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
  //      }
  //    }
  //
  //    map.put("isorderfeasible", "true");
  //    map.put("status", "Pending-CreditCheck");
  //
  //    client.newCompleteCommand(job.getKey()).variables(map).send();
  //  }

  @JobWorker(type = "ConfirmOrderNotification")
  public void handleJob(final JobClient client, final ActivatedJob job) {

    if (job.getBpmnProcessId().contains("Orderfullfillment")) {
      System.out.println("hello" + job.getBpmnProcessId());
      EmailService email = new EmailService();

      try {
        email.sendEmail(
            "akhila.puram@aaseya.com",
            "task notification",
            job.getBpmnProcessId() + "task has been assigned");
      } catch (Exception e) {
        System.out.println("Exception in email" + e.getMessage());
      }
    }

    Map<String, String> map = new HashMap<String, String>();
    map.put("orderedOptions", "testvariable");
    client.newCompleteCommand(job.getKey()).variables(map).send();
  }

  @JobWorker(type = "Activity_0p3o2cb", autoComplete = false)
  public void resumesubprocess(final JobClient client, final ActivatedJob job) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("resumesubprocess", "true");
    client.newCompleteCommand(job.getKey()).variables(map).send();
  }

  @JobWorker(type = "DeliverResourcesToBranch", autoComplete = false)
  public void completedeliverresources(final JobClient client, final ActivatedJob job) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("IsdeliveryCompleted", "true");
    System.out.println("---->>>delivery resources is completed");
    client.newCompleteCommand(job.getKey()).variables(map).send();
  }

  @JobWorker(type = "OrderFeasibilityCheck", autoComplete = false)
  public void OrderFeasibilityCheck(final JobClient client, final ActivatedJob job) {
    String isCustomerValidValue = "";
    System.out.println("Inside Order Feasibility Check" + job.getVariables());
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> variablesMap = job.getVariablesAsMap();
    /*
     * for (Map.Entry<String, Object> entry : variables.entrySet()) { if
     * (entry.getKey() == "isCustomerValid") { isCustomerValidValue = (String)
     * entry.getValue(); System.out.println("Key = " + entry.getKey() + ", Value = "
     * + entry.getValue()); } }
     */

    variablesMap.put("IsServiceAvailable", "true");
    variablesMap.put("status", "Pending-CreditCheck");
    variablesMap.put("hasBroadbandService", "true");

    /*
     * if (isCustomerValidValue.equals("true")) { System.out.println("Inside if");
     * map.put("isorderfeasible", "true"); map.put("status", "Pending-CreditCheck");
     * } else { System.out.println("Inside else"); map.put("isorderfeasible",
     * "false"); map.put("status", "Pending-CompleteData"); }
     */

    System.out.println("---map variables--" + map);
    client.newCompleteCommand(job.getKey()).variables(variablesMap).send();
  }

  @JobWorker(type = "SendInstallationNotification", autoComplete = false)
  public void NotifyCustomerinstallationdate(final JobClient client, final ActivatedJob job) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("IsNotificationsent", "true");
    System.out.println("---->>>notification sent successfully");
    client.newCompleteCommand(job.getKey()).variables(map).send();
  }

  @JobWorker(type = "InitiateBillingCycle", autoComplete = false)
  public void InitiateBillingCycle(final JobClient client, final ActivatedJob job) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("IsBillingCycleInitiated", "true");
    System.out.println("---->>>customer added to the billing cycle");
    client.newCompleteCommand(job.getKey()).variables(map).send();
  }

  @JobWorker(type = "InvokeBillingService", autoComplete = false)
  public void InvokeBillingService(final JobClient client, final ActivatedJob job) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("activationcompleted", "true");
    System.out.println("---->>>Service Activation Completed");
    client.newCompleteCommand(job.getKey()).variables(map).send();
  }

  @JobWorker(type = "ServiceActivation", autoComplete = false)
  public void InvokeServiceActivation(final JobClient client, final ActivatedJob job) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("activationcompleted", "true");
    System.out.println("---->>>Service Activation Completed");
    client.newCompleteCommand(job.getKey()).variables(map).send();
  }

  @JobWorker(type = "VoiceActivationService", autoComplete = false)
  public void VoiceActivationService(final JobClient client, final ActivatedJob job) {

    // String hasResourceInstallation = job.getVariable("hasResourceInstallation").toString();
    String hasVoiceService = job.getVariable("hasVoiceService").toString();
    Map<String, String> map = new HashMap<String, String>();
    boolean hasVoiceActivation = false;
    // boolean hasMultipleProducts = false;

    if (hasVoiceService.equalsIgnoreCase("true")) {
      // map.put("hasResourceInstallation", "true");
      System.out.println("---->>>Voice Activation Completed");
    } else {
      // map.put("hasResourceInstallation", "false");
      System.out.println("---->>>Voice Activation not Required");
    }

    client.newCompleteCommand(job.getKey()).variables(map).send();
  }

  @JobWorker(type = "NotifyOrderCompletion", autoComplete = false)
  public void NotifyOrderCompletion(final JobClient client, final ActivatedJob job) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("ordercompletionnotification", "true");
    System.out.println("---->>>Order Completion Notification sent to customer");
    client.newCompleteCommand(job.getKey()).variables(map).send();
  }

  @JobWorker(type = "RejectNotification", autoComplete = false)
  public void RejectNotification(final JobClient client, final ActivatedJob job) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("rejectNotificationStatus", "true");
    map.put("status", "OrderRejected");
    System.out.println("---->>>Order Reject Notification sent to customer");
    client.newCompleteCommand(job.getKey()).variables(map).send();
  }

  @JobWorker(type = "IncompleteInfoNotification", autoComplete = false)
  public void IncompleteInfoNotification(final JobClient client, final ActivatedJob job) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("IncompleteInfoNotification", "true");
    map.put("status", "Pending-UpdatedInfo");
    System.out.println("---->>>Order IncompleteInfo Notification sent to customer");
    client.newCompleteCommand(job.getKey()).variables(map).send();
  }

  @JobWorker(type = "TaskRemainderNotification")
  public void TaskRemainderNotification(final JobClient client, final ActivatedJob job) {

    System.out.println("---->>>Remainder mail for task completion");
  }
}
