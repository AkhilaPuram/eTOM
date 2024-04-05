package com.tom.OrderManagementServices.controller;

import com.tom.OrderManagementServices.OMSConstants;
import com.tom.OrderManagementServices.dto.CreateOrderRequest;
import com.tom.OrderManagementServices.model.Customer;
import com.tom.OrderManagementServices.model.Order;
import com.tom.OrderManagementServices.model.OrderItem;
import com.tom.OrderManagementServices.process.TaskVariables;
import com.tom.OrderManagementServices.repository.CustomerRepository;
import com.tom.OrderManagementServices.service.OrderProcess;
import com.tom.OrderManagementServices.service.OrderService;
import com.tom.OrderManagementServices.service.UserInfoService;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/etom/")
public class StartOrderFullfillment {
  @Autowired private ZeebeClient client;
  @Autowired private OrderProcess OP;
  @Autowired OrderService orderService;
  @Autowired CustomerRepository customerRepository;
  @Autowired UserInfoService userInfoService;

  @PostMapping(value = "/createorder")
  public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest createrequest) {

    System.out.println("from req" + createrequest);

    // createCustomer(createrequest);

    Order order = orderService.createOrder(createrequest);

    Map<String, String> processVariables = new HashMap<String, String>();
    processVariables.put("customerID", String.valueOf(order.getCustomer().getId()));
    processVariables.put("orderID", order.getId().toString());
    processVariables.put("emailID", order.getCustomer().getEmailID());
    processVariables.put("firstName", order.getCustomer().getFirstName());
    processVariables.put("lastName", order.getCustomer().getLastName());
    processVariables.put("middleName", order.getCustomer().getMiddleName());
    processVariables.put("homePhone", order.getCustomer().getHomePhone());
    processVariables.put("title", order.getCustomer().getTitle());
    processVariables.put("suffix", order.getCustomer().getSuffix());
    processVariables.put("fax", order.getCustomer().getFax());
    processVariables.put("businessPhone", order.getCustomer().getBusinessPhone());
    processVariables.put("customerType", order.getCustomer().getCustomrType());
    processVariables.put("hasResourceInstallation", "false");
    processVariables.put("hasVoiceService", "false");
    System.out.println("Resources generated successfully");
    List<OrderItem> orderItem = createrequest.getOrderitem();
    Iterator<OrderItem> orderItemIter = orderItem.iterator();
    System.out.println("---OrderItem---->" + orderItem);

    while (orderItemIter.hasNext()) {

      String orderItemType = orderItemIter.next().getServiceType();

      if (orderItemType.equals("Fiber")) {

        processVariables.put("hasResourceInstallation", "true");
      }
      if (orderItemType.equalsIgnoreCase("Prepaid") || orderItemType.equalsIgnoreCase("Postpaid")) {

        processVariables.put("hasVoiceService", "true");
      }
    }

    JSONObject jsonObject = new JSONObject(order.getCustomer());
    processVariables.put("Customer", jsonObject.toString());

    ProcessInstanceEvent processInstanceEvent =
        client
            .newCreateInstanceCommand()
            .bpmnProcessId(OMSConstants.BPMN_PROCESS_ID)
            .latestVersion()
            .variables(processVariables)
            .send()
            .join();

    return ResponseEntity.status(HttpStatus.OK)
        .body("Started: " + processInstanceEvent.getProcessInstanceKey());
  }

  public void createCustomer(CreateOrderRequest createrequest) {

    String userName = createrequest.getCustomer().getEmailID();
    String firstName = createrequest.getCustomer().getFirstName();
    String lastName = createrequest.getCustomer().getLastName();
    String emailID = createrequest.getCustomer().getEmailID();

    List<Customer> customerlist =
        customerRepository.findByEmailID(createrequest.getCustomer().getEmailID());

    System.out.println("-----customer esiste-->" + customerlist);
    if (customerlist.isEmpty()) {

      HttpComponentsClientHttpRequestFactory requestFactory =
          new HttpComponentsClientHttpRequestFactory();

      String AuthToken = "Bearer " + userInfoService.getKeyCloakToken();
      String uri = "http://localhost:18080/auth/admin/realms/camunda-platform/users";
      TaskVariables[] task = null;

      MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
      ResponseEntity<TaskVariables[]> resEntity = null;
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.setRequestFactory(requestFactory);

      headers.add("Authorization", AuthToken);
      headers.add("Content-Type", "application/json");

      String requestJson =
          "{\"attributes\":{\"role\": \"customer\"},\r\n\"credentials\": [{\r\n    \"temporary\": true,\r\n    \"type\": \"password\", \r\n    \"value\": \"TESTpSSWrd\"\r\n}],\r\n\"username\":\""
              + userName
              + "\",\r\n\"firstName\":\""
              + firstName
              + "\", \r\n\"lastName\":\""
              + lastName
              + "\", \r\n\r\n\"email\": \""
              + emailID
              + "\",\r\n\"emailVerified\": false,\r\n\"enabled\": true\r\n}";

      HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);

      resEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, TaskVariables[].class);

      System.out.println("----resEntity---->" + resEntity.getBody());
    }
  }
}
