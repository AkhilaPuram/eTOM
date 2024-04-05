package com.tom.OrderManagementServices.dto;

import java.util.Arrays;

public class Attribute {

  private String group[];

  public String[] getGroup() {
    return group;
  }

  public void setGroup(String[] group) {
    this.group = group;
  }

  @Override
  public String toString() {
    return "Attribute [group=" + Arrays.toString(group) + "]";
  }

  public Attribute(String[] group) {
    super();
    this.group = group;
  }

  public Attribute() {
    super();
    // TODO Auto-generated constructor stub
  }
}
