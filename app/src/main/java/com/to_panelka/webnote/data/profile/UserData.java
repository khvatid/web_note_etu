package com.to_panelka.webnote.data.profile;


public class UserData{
  private String login;
  private String mail;
  private String fullName;
  private String description;

  public UserData(String login, String mail, String fullName) {
    this.login = login;
    this.mail = mail;
    this.fullName = fullName;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLogin() {
    return login;
  }

  public String getMail() {
    return mail;
  }

  public String getFullName() {
    return fullName;
  }

  public String getDescription() {
    return description;
  }
}
