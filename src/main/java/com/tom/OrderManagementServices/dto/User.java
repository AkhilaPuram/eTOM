package com.tom.OrderManagementServices.dto;

public class User {

  private String username;
  private String email;
  private String id;
  private Attribute attributes;

  public User(String username, String email, String id, Attribute attributes) {
    super();
    this.username = username;
    this.email = email;
    this.id = id;
    this.attributes = attributes;
  }

  public Attribute getAttributes() {
    return attributes;
  }

  public void setAttributes(Attribute attributes) {
    this.attributes = attributes;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public User() {
    super();
    // TODO Auto-generated constructor stub
  }

  @Override
  public String toString() {
    return "User [username="
        + username
        + ", email="
        + email
        + ", id="
        + id
        + ", attributes="
        + attributes
        + "]";
  }
}
