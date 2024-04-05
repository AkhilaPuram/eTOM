package com.tom.OrderManagementServices.controller;

import com.tom.OrderManagementServices.model.Billing;
import com.tom.OrderManagementServices.repository.BillingRepository;
import com.tom.OrderManagementServices.service.GetBilling;
import com.tom.OrderManagementServices.service.OrderProcess;
import io.camunda.zeebe.client.ZeebeClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BillingController {
  @Autowired private ZeebeClient zeebe;
  @Autowired private OrderProcess OP;
  @Autowired private BillingRepository billing_repository;

  /*
   * @PostMapping("/insertBilling") public ResponseEntity<CreateResponse>
   * startLoanProcess(@RequestBody CreateRequest createrequest) {
   *
   * System.out.println("from postman" + createrequest); String processID =
   * OP.createProcess(createrequest);
   *
   * CreateResponse startResponse = new CreateResponse();
   * startResponse.setProcessInstanceKey(processID); HttpHeaders responseheaders =
   * new HttpHeaders();
   * responseheaders.setContentType(MediaType.APPLICATION_JSON); return
   * ResponseEntity.status(HttpStatus.OK).headers(responseheaders).body(
   * startResponse); }
   */
  @GetMapping("/getbilling")
  public ResponseEntity<List<Billing>> startLoanProcess() {
    HttpHeaders responseheaders = new HttpHeaders();

    responseheaders.setContentType(MediaType.APPLICATION_JSON);

    GetBilling billingservice = new GetBilling();

    return ResponseEntity.status(HttpStatus.OK)
        .headers(responseheaders)
        .body(billingservice.getBillings(billing_repository));
  }
}
