package com.tom.OrderManagementServices.controller;

import com.tom.OrderManagementServices.dto.TaskInfo;
import com.tom.OrderManagementServices.process.TaskVariables;
import com.tom.OrderManagementServices.service.TaskListService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tasklist")
public class TaskListController {
  // @Autowired private TaskListService tasklist;
  @Autowired TaskListService taskListService;
  private TaskListService tasklist = new TaskListService();

  @Value("${spring.datasource.password}")
  private String searchurl;

  @GetMapping("/{id}/gettask")
  public String getActiveTaskID(@PathVariable("id") String ProcessID) {
    String TaskID = "";
    TaskID = tasklist.getActiveTaskID(ProcessID);
    return TaskID;
  }

  @PatchMapping("/CompleteTask/{TaskID}")
  public String CompleteTaskByID(
      @PathVariable("TaskID") String id, @RequestBody Map<String, Object> variables) {
    String TaskCompletionResponse = "";
    System.out.println("variable from request" + variables);

    TaskCompletionResponse = tasklist.CompleteTaskByID(id, variables);
    return TaskCompletionResponse;
  }

  @GetMapping("/GetAllOpenOrders/{userGroup}")
  public ResponseEntity<TaskVariables[]> GetOpenOrders(
      @PathVariable("userGroup") String userGroup) {
    System.out.println("inside all orders" + searchurl);

    TaskVariables[] tasksInfo = tasklist.GetOpenOrders("", userGroup);
    HttpHeaders responseheaders = new HttpHeaders();
    responseheaders.setContentType(MediaType.APPLICATION_JSON);
    return ResponseEntity.status(HttpStatus.OK).headers(responseheaders).body(tasksInfo);
  }

  @GetMapping("/GetAllOpenOrders")
  public ResponseEntity<List> GetOpenOrders1() {
    System.out.println("inside all orders" + searchurl);

    List<TaskVariables> tasksInfo = tasklist.GetOpenOrders("");
    HttpHeaders responseheaders = new HttpHeaders();
    responseheaders.setContentType(MediaType.APPLICATION_JSON);
    return ResponseEntity.status(HttpStatus.OK).headers(responseheaders).body(tasksInfo);
  }

  /*
   * @GetMapping("/GetAllOpenServiceOrders") public ArrayList<TaskVariables>
   * GetOpenServiceOrders() { System.out.println("inside all orders"); String
   * Process = "ServiceReview"; return tasklist.GetOpenOrders(Process); }
   *
   * @GetMapping("/GetAllOpenResourceOrders") public ArrayList<TaskVariables>
   * GetOpenResourdeOrders() { System.out.println("inside all orders"); String
   * Process = "ResourceReview"; return tasklist.GetOpenOrders(Process); }
   */

  @GetMapping("/{id}/gettaskinfo")
  public ResponseEntity<TaskInfo> getTaskInfo(@PathVariable("id") String taskID) {
    TaskInfo taskInfo = null;
    System.out.println("-------taskID---" + taskID);
    taskInfo = taskListService.getTaskInfo(taskID);
    HttpHeaders responseheaders = new HttpHeaders();
    responseheaders.setContentType(MediaType.APPLICATION_JSON);
    System.out.println("TaskInfo--------------" + taskInfo);
    return ResponseEntity.status(HttpStatus.OK).headers(responseheaders).body(taskInfo);
  }
}
