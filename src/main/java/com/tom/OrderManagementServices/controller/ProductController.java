package com.tom.OrderManagementServices.controller;

import com.tom.OrderManagementServices.model.Product;
import com.tom.OrderManagementServices.repository.ProductRepository;
import com.tom.OrderManagementServices.service.GetProduct;
import com.tom.OrderManagementServices.service.OrderProcess;
import io.camunda.zeebe.client.ZeebeClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
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
public class ProductController {
  @Autowired private ZeebeClient zeebe;
  @Autowired private OrderProcess OP;
  @Autowired public ProductRepository product_repository;

  /*
   * @PostMapping("/insertproduct") public ResponseEntity<CreateResponse>
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
  @GetMapping("/getproducts")
  public ResponseEntity<String> startLoanProcess() {
    HttpHeaders responseheaders = new HttpHeaders();

    responseheaders.setContentType(MediaType.APPLICATION_JSON);
    GetProduct productservice = new GetProduct();

    ArrayList<String> prdCategoryList = new ArrayList<String>();
    prdCategoryList.add("Prepaid");
    prdCategoryList.add("Postpaid");
    prdCategoryList.add("Fiber");

    Iterator<String> prdCategoryListIter = prdCategoryList.iterator();

    JSONObject jsonObj = new JSONObject();

    List<Product> productList = productservice.getProducts(product_repository);

    // System.out.println("--------product:List------" + productList);

    while (prdCategoryListIter.hasNext()) {

      String productType = prdCategoryListIter.next();
      Iterator<Product> productListIter = productList.iterator();
      JSONArray jab = new JSONArray();
      while (productListIter.hasNext()) {

        //   System.out.println("-------productType-------" + productType);
        Product productObj = productListIter.next();
        Map<String, String> prdObj = null;
        if (productType.equals(productObj.getCategory())) {

          prdObj = new HashMap<String, String>();

          prdObj.put("data", productObj.getData());
          prdObj.put("validity", productObj.getValidity());
          prdObj.put("speed", productObj.getSpeed());
          prdObj.put("voice", productObj.getVoice());
          prdObj.put("price", productObj.getPrice());
          System.out.println("===" + prdObj);
          jab.put(prdObj);
        }

        //
      }

      jsonObj.put(productType, jab);
    }
    // System.out.println("---------jsonObj----" + jsonObj);
    return ResponseEntity.status(HttpStatus.OK).headers(responseheaders).body(jsonObj.toString());
  }
}
