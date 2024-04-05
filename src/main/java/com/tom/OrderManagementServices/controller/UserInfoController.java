package com.tom.OrderManagementServices.controller;

import com.tom.OrderManagementServices.dto.User;
import com.tom.OrderManagementServices.repository.ProductRepository;
import com.tom.OrderManagementServices.service.OrderProcess;
import com.tom.OrderManagementServices.service.UserInfoService;
import io.camunda.zeebe.client.ZeebeClient;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserInfoController {
  @Autowired private ZeebeClient zeebe;
  @Autowired private OrderProcess OP;
  @Autowired public ProductRepository product_repository;
  @Autowired public UserInfoService userInfoService;

  @GetMapping("/GetUserGroup/{emailID}")
  public ResponseEntity<String[]> getUserGroup(@PathVariable("emailID") String emailID) {

    String uri = "http://10.13.1.180:18080/auth/admin/realms/camunda-platform/users";

    User user = new User();
    List<User> userList = new ArrayList<User>();

    ResponseEntity<User[]> resEntity = null;
    RestTemplate restTemplate = new RestTemplate();
    String usergroup[] = null;
    try {
      HttpComponentsClientHttpRequestFactory requestFactory =
          new HttpComponentsClientHttpRequestFactory();

      requestFactory.setConnectTimeout(0);
      requestFactory.setReadTimeout(0);
      restTemplate.setRequestFactory(requestFactory);
      System.out.println("get User URI::: " + uri);
      MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

      String AuthToken = "Bearer " + userInfoService.getKeyCloakToken();

      headers.add("Authorization", AuthToken);
      headers.add("Content-Type", "application/json");

      resEntity =
          restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<Object>(headers), User[].class);

      System.out.println(" User ID" + resEntity.getBody());

      User[] userArr = resEntity.getBody();

      for (int i = 0; i < userArr.length; i++) {

        System.out.println("email--" + userArr[i].getEmail());
        if (emailID.equals(userArr[i].getEmail())) {
          // userID = userArr[i].getId();
          System.out.println("Username--" + userArr[i].toString());
          System.out.println("UserID--" + userArr[i].getId());

          System.out.println("GroupName--" + userArr[i].getAttributes().getGroup().toString());
          usergroup = userArr[i].getAttributes().getGroup();

          break;
        }
      }

    } catch (Exception eek) {
      System.out.println("** Exception: " + eek.getMessage());
    }
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Content-Type", "application/json");
    return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body(usergroup);
  }
}
