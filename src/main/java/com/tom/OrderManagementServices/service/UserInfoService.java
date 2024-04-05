package com.tom.OrderManagementServices.service;

import com.tom.OrderManagementServices.OMSConstants;
import com.tom.OrderManagementServices.dto.AccessToken;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class UserInfoService {

  public String getKeyCloakToken() {
    String token = "";
    String uri =
        "http://10.13.1.180:18080/auth/realms/camunda-platform/protocol/openid-connect/token";

    System.out.println(" inside Patch ::" + uri);

    RestTemplate restTemplate = new RestTemplate();
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
      // map.add("email", "first.last@example.com");
      map.add("client_secret", OMSConstants.KEYCLOAK_CLIENT_SECRET);
      map.add("client_id", OMSConstants.KEYCLOAK_CLIENT_ID);
      map.add("grant_type", "client_credentials");

      HttpEntity<MultiValueMap<String, String>> request =
          new HttpEntity<MultiValueMap<String, String>>(map, headers);

      ResponseEntity<AccessToken> response =
          restTemplate.exchange(uri, HttpMethod.POST, request, AccessToken.class);

      token = response.getBody().getAccess_token();

      System.out.println(" accss toekn ::: " + response.getBody().getAccess_token());
      System.out.println(
          "Result - status (" + response.getStatusCode() + ") has body: " + response.hasBody());
      ResponseEntity<List> resentity = null;

    } catch (Exception eek) {
      System.out.println("** Exception: " + eek.getMessage());
    }
    return token;
  }
}
