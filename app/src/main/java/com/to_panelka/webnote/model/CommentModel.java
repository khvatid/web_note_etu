package com.to_panelka.webnote.model;

public class CommentModel {

  String nameUser;
  String idUser;
  String textComment;
  String idComment;

  public CommentModel() {
  }

  public CommentModel(String nameUser, String idUser, String textComment, String idComment) {
    this.nameUser = nameUser;
    this.idUser = idUser;
    this.textComment = textComment;
    this.idComment = idComment;
  }

  public void setIdComment(String idComment) { this.idComment = idComment; }

  public void setNameUser(String nameUser) {
    this.nameUser = nameUser;
  }

  public void setIdUser(String idUser) {
    this.idUser = idUser;
  }

  public void setTextComment(String textComment) {
    this.textComment = textComment;
  }

  public String getNameUser() {
    return nameUser;
  }

  public String getIdUser() {
    return idUser;
  }

  public String getIdComment() { return idComment; }

  public String getTextComment() {
    return textComment;
  }
}
