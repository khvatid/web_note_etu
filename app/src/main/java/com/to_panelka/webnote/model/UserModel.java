package com.to_panelka.webnote.model;

public class UserModel {

  String id;
  String name;
  String mail;
  String description;

  public UserModel(String id, String name, String mail, String description) {
    this.id = id;
    this.name = name;
    this.mail = mail;
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getMail() {
    return mail;
  }

  public String getDescription() {
    return description;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
