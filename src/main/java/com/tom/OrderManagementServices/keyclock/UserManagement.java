package com.tom.OrderManagementServices.keyclock;

import com.tom.OrderManagementServices.OMSConstants;
import com.tom.OrderManagementServices.dto.AccessToken;
import com.tom.OrderManagementServices.dto.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserManagement {
  public String getKeyCloakToken() {
    String token = "";
    String uri = OMSConstants.TOKEN_URL;
    System.out.println(" inside Patch ::" + uri);
    RestTemplate restTemplate = new RestTemplate();
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

      map.add("client_secret", "4l9LCbPYz9cjS93tX9QhIIdYAA0Yveul");
      map.add("client_id", "OrderFullfillment");
      map.add("grant_type", "client_credentials");
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

  @GetMapping("/GetGroupAttribute/{email}")
  public ResponseEntity<String> getUsers(@PathVariable("email") String email) {

    String uri = "http://10.13.1.180:18080/auth/admin/realms/camunda-platform/users?email=" + email;
    User user = new User();
    ResponseEntity<String> Resentity = null;
    ResponseEntity<User[]> Resentity1 = null;
    RestTemplate restTemplate = new RestTemplate();
    String Group = "";
    try {

      HttpComponentsClientHttpRequestFactory requestFactory =
          new HttpComponentsClientHttpRequestFactory();
      requestFactory.setConnectTimeout(0);
      requestFactory.setReadTimeout(0);
      restTemplate.setRequestFactory(requestFactory);
      System.out.println("get User URI::: " + uri);
      MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
      String AuthToken = "Bearer " + getKeyCloakToken();
      headers.add("Authorization", AuthToken);
      headers.add("Content-Type", "application/json");
      Resentity1 =
          restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<Object>(headers), User[].class);
      User[] userArray = Resentity1.getBody();

      for (int i = 0; i < userArray.length; i++) {

        System.out.println("email--" + userArray[i].getEmail());
        System.out.println("attributes:" + userArray[i].getAttributes().getGroup()[0]);
        Group = userArray[i].getAttributes().getGroup()[0];
        break;
      }

      /*
       * JSONObject base = new JSONObject(response); JSONArray dataContent =
       * base.getJSONArray("data");
       *
       * JSONParser parser = new JSONParser(); JSONArray data =
       * jsonObj.getJSONArray("Resentity.getBody()"); JSONObject jsonObject =
       * (JSONObject) parser.parse(Resentity.getBody()); String UserID = (String)
       * jsonObject.get("id"); System.out.println(" User ID" + UserID)
       */ ;
    } catch (Exception eek) {
      System.out.println("** Exception: " + eek.getMessage());
    }

    HttpHeaders responseHeaders = new HttpHeaders();
    // responseHeaders.set("Content-Type", "application/json");
    return new ResponseEntity<String>(Group, responseHeaders, HttpStatus.CREATED);
  }
}
