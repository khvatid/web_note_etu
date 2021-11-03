package com.to_panelka.webnote.model;

import com.google.firebase.Timestamp;

public class PostModel {

  String idPost;
  String idUser;
  String nameUser;
  String textPost;
  Timestamp timePublish;



  public PostModel(){ }
  public PostModel(String idPost, String idUser, String nameUser, String textPost,
      Timestamp timePublish) {
    this.idPost = idPost;
    this.idUser = idUser;
    this.nameUser = nameUser;
    this.textPost = textPost;
    this.timePublish = timePublish;
  }

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

  public void setNameUser(String nameUser) {
    this.nameUser = nameUser;
  }

  public String getNameUser() {
    return nameUser;
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


}
