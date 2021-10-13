package com.to_panelka.webnote.model;

import com.google.firebase.Timestamp;

public class PostModel {

  String idPost;
  String idUser;
  String textPost;
  Timestamp timePublish;

  public void setIdPost(String idPost) {
    this.idPost = idPost;
  }

  public void setIdUser(String idUser) {
    this.idUser = idUser;
  }

  public void setText(String text) {
    this.textPost = text;
  }

  public void setTimePublish(Timestamp time) {
    this.timePublish = time;
  }

  public String getIdPost() {
    return idPost;
  }

  public String getIdUser() {
    return idUser;
  }

  public String getTextPost() {
    return textPost;
  }

  public Timestamp getTimePublish() {
    return timePublish;
  }

  public PostModel(String idPost, String idUser, String text, Timestamp time) {
    this.idPost = idPost;
    this.idUser = idUser;
    this.textPost = text;
    this.timePublish = time;
  }

  public PostModel(){

  }
}
