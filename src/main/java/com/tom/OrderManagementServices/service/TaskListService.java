package com.tom.OrderManagementServices.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tom.OrderManagementServices.OMSConstants;
import com.tom.OrderManagementServices.dto.AccessToken;
import com.tom.OrderManagementServices.dto.TaskInfo;
import com.tom.OrderManagementServices.model.*;
import com.tom.OrderManagementServices.model.Customer;
import com.tom.OrderManagementServices.process.TaskVariables;
import com.tom.OrderManagementServices.repository.AddressRepository;
import com.tom.OrderManagementServices.repository.CustomerRepository;
import com.tom.OrderManagementServices.repository.OrderItemRepository;
import com.tom.OrderManagementServices.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class TaskListService {

  @Autowired OrderItemRepository orderItemRepository;
  @Autowired CustomerRepository customerRepository;
  @Autowired OrderRepository orderRepository;
  @Autowired AddressRepository addressRepository;
  @Autowired CustomerService customerService;
  @Autowired OrderService orderService;

  private String client_secret;
  private String accessTokenUri;
  private String granttype;
  private String clientID;

  @Value("${spring.datasource.password}")
  private String searchurl;

  private String id;

  public TaskListService() {
    // super();
  }

  public String getTasklistToken() {
    String token = "";

    RestTemplate restTemplate = new RestTemplate();
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
      map.add("client_secret", OMSConstants.CLIENT_SECRET);
      map.add("client_id", OMSConstants.CLIENT_ID);
      map.add("grant_type", OMSConstants.GRANT_TYPE);
      HttpEntity<MultiValueMap<String, String>> request =
          new HttpEntity<MultiValueMap<String, String>>(map, headers);

      ResponseEntity<AccessToken> response =
          restTemplate.exchange(
              OMSConstants.TOKEN_URL, HttpMethod.POST, request, AccessToken.class);

      token = response.getBody().getAccess_token();
    } catch (Exception eek) {
      System.out.println("** Exception: " + eek.getMessage());
    }

    return token;
  }

  public String getActiveTaskID(String ProcessID) {
    String TaskID = "";

    ResponseEntity<TaskVariables[]> Resentity = null;
    ArrayList<TaskVariables> Task1 = new ArrayList<TaskVariables>();
    RestTemplate restTemplate = new RestTemplate();
    try {

      HttpComponentsClientHttpRequestFactory requestFactory =
          new HttpComponentsClientHttpRequestFactory();
      requestFactory.setConnectTimeout(0);
      requestFactory.setReadTimeout(0);
      restTemplate.setRequestFactory(requestFactory);
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      String AuthToken = "Bearer " + getTasklistToken();
      headers.add("Authorization", AuthToken);

      String requestJson =
          "{\"taskState\":\"CREATED\",\"processInstanceKey\":\"" + ProcessID + "\"}";
      HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);

      Resentity =
          restTemplate.exchange(
              OMSConstants.SEARCH_URL, HttpMethod.POST, entity, TaskVariables[].class);

      TaskVariables[] task = Resentity.getBody();
      for (int i = 0; i < task.length; i++) {
        /*
         * System.out.println( "Results :" + i + " " + task[i].getId() + " for process "
         * + task[i].getProcessInstanceKey() + "task name:" + task[i].getName() +
         * "Task State" + task[i].getTaskState());
         */
        if (task[i].getProcessInstanceKey().equals(ProcessID)
            && task[i].getTaskState().equals("CREATED")) {
          System.out.println(" Match found at " + i + task[i].getName());

          TaskID += task[i].getId() + ",";
        }
      }

    } catch (Exception eek) {
      System.out.println("** Exception: " + eek.getMessage());
    }

    return TaskID;
  }

  public String CompleteTaskByID(String id, Map<String, Object> variables) {
    String uri = OMSConstants.BASE_TASK_URL + id + OMSConstants.COMPLETE_URL;
    ResponseEntity<String> Resentity = null;
    ResponseEntity<String> response = null;
    System.out.println(" Complete Varaibles" + variables);
    System.out.println("Complete request" + variables.toString().contains("Approve"));
    String Response = null;
    RestTemplate restTemplate = new RestTemplate();
    try {

      HttpComponentsClientHttpRequestFactory requestFactory =
          new HttpComponentsClientHttpRequestFactory();
      requestFactory.setConnectTimeout(0);
      requestFactory.setReadTimeout(0);
      restTemplate.setRequestFactory(requestFactory);
      System.out.println("complete task URI::: " + uri);
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      String AuthToken = "Bearer " + getTasklistToken();
      headers.add("Authorization", AuthToken);
      String requestJson = "";
      ObjectMapper mapper = new ObjectMapper();
      String MapperRequest = "";
      try {
        MapperRequest = mapper.writeValueAsString(variables);

        System.out.println("JSON request" + MapperRequest);
      } catch (Exception e) {
        System.out.println("JSON request exception");
      }
      if (variables.toString().contains("Approve")) {
        requestJson = "{\"variables\": [{\"name\": \"isApprove\",\"value\": \"\\\"true\\\"\"}]}";
      } else if (variables.toString().contains("Reject")) {
        requestJson = "{\"variables\": [{\"name\": \"isApprove\",\"value\": \"\\\"false\\\"\"}]}";
      } else {
        requestJson = "{\"variables\": [{\"name\": \"ProcessID\",\"value\": \"\\\"hello\\\"\"}]}";
      }

      headers.setContentType(MediaType.APPLICATION_JSON);
      String Dummy =
          "{\"variables\": [{\"name\": \"strVarExample\",\"value\": \"\\\"hello\\\"\"},{\"name\": \"intVarExample\",\"value\": \"15\"},{\"name\": \"booleanVarExample\",\"value\": \"false\"},{\"name\": \"arrayVarExample\",\"value\": \"[1, 2, 3, 5, 8, 13, 21]\"}]}";

      HttpEntity<String> entity = new HttpEntity<String>(MapperRequest, headers);
      System.out.println("BBB:" + entity.getBody());
      response = restTemplate.exchange(uri, HttpMethod.PATCH, entity, String.class);
      System.out.println("hello");
    } catch (Exception eek) {
      System.out.println("** Exception: " + eek.getMessage());
    }
    return response.getBody();
  }

  public ArrayList<TaskVariables> GetOpenOrders(String process) {

    if (process.equals("")) {
      process = "Order";
    }

    ResponseEntity<TaskVariables[]> Resentity = null;
    ArrayList<TaskVariables> Task1 = new ArrayList<TaskVariables>();
    String Response = null;

    RestTemplate restTemplate = new RestTemplate();
    try {

      HttpComponentsClientHttpRequestFactory requestFactory =
          new HttpComponentsClientHttpRequestFactory();
      requestFactory.setConnectTimeout(0);
      requestFactory.setReadTimeout(0);
      restTemplate.setRequestFactory(requestFactory);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      String AuthToken = "Bearer " + getTasklistToken();
      headers.add("Authorization", AuthToken);

      String requestJson =
          "{\"taskState\":\"CREATED\",\"includeVariables\": [\r\n"
              + "    {\r\n"
              + "      \"name\": \"firstName\"\r\n"
              + "      },\r\n"
              + "       {\r\n"
              + "      \"name\": \"orderID\"\r\n"
              + "      },\r\n"
              + "      {\r\n"
              + "        \"name\":\"status\"\r\n"
              + "      }]}";

      HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
      Resentity =
          restTemplate.exchange(
              OMSConstants.SEARCH_URL, HttpMethod.POST, entity, TaskVariables[].class);
      TaskVariables[] task = Resentity.getBody();
      System.out.println("Fetch all orders" + Resentity.getBody());
      for (int i = 0; i < task.length; i++) {

        // System.out.println("result " + i + " " + task[i]); String taskid =
        task[i].getId();
        String processID = task[i].getProcessInstanceKey();
        String TaskName = task[i].getName();
        if (task[i].getProcessName().contains(process)
            && (task[i].getTaskState() == null
                || task[i].getTaskState().equalsIgnoreCase("CREATED"))) {

          Task1.add(task[i]);
          System.out.println(
              "Process ID"
                  + processID
                  + "State "
                  + task[i].getState()
                  + "  Task State"
                  + task[i].getTaskState());
        }
      }
      //  System.out.println("result count" + task1.length);

    } catch (Exception e) {
      System.out.println("exception in get tasks " + e.getMessage());
    }
    return Task1;
  }

  public TaskInfo getTaskInfo(String taskID) {

    ResponseEntity<TaskVariables[]> resEntity = null;
    ArrayList<TaskVariables> Task1 = new ArrayList<TaskVariables>();
    RestTemplate restTemplate = new RestTemplate();

    Order order = null;
    Customer customer = null;
    TaskInfo taskInfo = new TaskInfo();
    List<OrderItem> orderItems = null;

    try {

      HttpComponentsClientHttpRequestFactory requestFactory =
          new HttpComponentsClientHttpRequestFactory();

      requestFactory.setConnectTimeout(0);
      requestFactory.setReadTimeout(0);
      restTemplate.setRequestFactory(requestFactory);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      String AuthToken = "Bearer " + getTasklistToken();
      headers.add("Authorization", AuthToken);

      String requestJson = "";
      String Task_INFO_URL =
          OMSConstants.BASE_TASK_URL + taskID + OMSConstants.SEARCH_TASK_VARIABLES;

      HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);

      resEntity =
          restTemplate.exchange(Task_INFO_URL, HttpMethod.POST, entity, TaskVariables[].class);

      TaskVariables[] task = resEntity.getBody();
      System.out.println("-----------response body----" + task);

      for (int i = 0; i < task.length; i++) {

        //        System.out.println(
        //            " Match found at " + i + task[i].getName() + "----Task Value----" +
        // task[i].getValue());

        if (task[i].getName().equals("customerID")) {

          String customerID = task[i].getValue().substring(1, task[i].getValue().length() - 1);

          customer = customerService.getCustomer(customerID);

          System.out.println("---Customer----" + customer);
        } else if (task[i].getName().equals("orderID")) {

          String orderID = task[i].getValue().substring(1, task[i].getValue().length() - 1);

          order = orderService.getOrder(orderID);
          order.setCustomer(customer);
          System.out.println("---------order.getId()---------" + order.getId());
          orderItems = orderItemRepository.findByOrderId(order.getId());
          System.out.println("---Order--Items--" + orderItems);
        }
      }
    } catch (Exception eek) {
      System.out.println("** Exception: " + eek.getMessage());
    }

    System.out.println("----------Order----" + order);
    taskInfo.setOrder(order);
    taskInfo.setOrderitem(orderItems);

    return taskInfo;
  }

  public String getKeyCloakToken() {
    String token = "";
    String uri =
        "http://localhost:18080/auth/realms/camunda-platform/protocol/openid-connect/token";
    System.out.println(" inside Patch ::" + uri);
    RestTemplate restTemplate = new RestTemplate();
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

      map.add("client_secret", OMSConstants.KEYCLOAK_CLIENT_SECRET);
      map.add("client_id", OMSConstants.KEYCLOAK_CLIENT_ID);
      map.add("grant_type", OMSConstants.GRANT_TYPE);

      HttpEntity<MultiValueMap<String, String>> request =
          new HttpEntity<MultiValueMap<String, String>>(map, headers);

      ResponseEntity<AccessToken> response =
          restTemplate.exchange(uri, HttpMethod.POST, request, AccessToken.class);
      token = response.getBody().getAccess_token();

      System.out.println(" accss toekn ::: " + response.getBody().getAccess_token());
      System.out.println(
          "Result - status (" + response.getStatusCode() + ") has body: " + response.hasBody());
    } catch (Exception eek) {
      System.out.println("** Exception: " + eek.getMessage());
    }

    return token;
  }

  public TaskVariables[] GetOpenOrders(String process, String userGroup) {

    if (process.equals("")) {
      process = "Order";
    }
    TaskVariables[] task = null;
    ResponseEntity<TaskVariables[]> Resentity = null;
    ArrayList<TaskVariables> Task1 = new ArrayList<TaskVariables>();
    String Response = null;

    RestTemplate restTemplate = new RestTemplate();

    try {

      HttpComponentsClientHttpRequestFactory requestFactory =
          new HttpComponentsClientHttpRequestFactory();

      requestFactory.setConnectTimeout(0);
      requestFactory.setReadTimeout(0);
      restTemplate.setRequestFactory(requestFactory);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      String AuthToken = "Bearer " + getTasklistToken();
      headers.add("Authorization", AuthToken);

      String requestJson = "{\"state\":\"CREATED\",\"candidateGroup\":\"" + userGroup + "\"}";

      System.out.println("----requestJson-->" + requestJson);

      HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);

      Resentity =
          restTemplate.exchange(
              OMSConstants.SEARCH_URL, HttpMethod.POST, entity, TaskVariables[].class);

      task = Resentity.getBody();

      /*
       * for (int i = 0; i < task.length; i++) {
       *
       * // System.out.println("result " + i + " " + task[i]); String taskid =
       * task[i].getId(); String processID = task[i].getProcessInstanceKey(); String
       * TaskName = task[i].getName(); //
       *
       * if (task[i].getProcessName().contains(process) && (task[i].getTaskState() ==
       * null || task[i].getTaskState().equalsIgnoreCase("CREATED"))) {
       *
       * Task1.add(task[i]); System.out.println( "Process ID" + processID + "State " +
       * task[i].getState() + "  Task State" + task[i].getTaskState()); } }
       */
      // System.out.println("result count" + Task1.length);

    } catch (Exception e) {
      System.out.println("exception in get tasks " + e.getMessage());
    }
    return task;
  }
}
