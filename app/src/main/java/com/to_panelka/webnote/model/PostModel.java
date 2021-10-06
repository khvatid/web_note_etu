package com.to_panelka.webnote.model;

public class PostModel {

  String postId;
  String userId;
  String text;
  String time;

  public void setPostId(String postId) {
    this.postId = postId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getPostId() {
    return postId;
  }

  public String getUserId() {
    return userId;
  }

  public String getText() {
    return text;
  }

  public String getTime() {
    return time;
  }

  public PostModel(String postId, String userId, String text, String time) {
    this.postId = postId;
    this.userId = userId;
    this.text = text;
    this.time = time;
  }
}
