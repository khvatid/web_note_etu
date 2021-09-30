package com.to_panelka.webnote.pojo;

import java.util.Objects;

public class Post {
  private User user;
  private long id;
  private String text;
  private  String time;
  private int likes;

  public Post(User user, long id, String text, String time, int likes) {
    this.user = user;
    this.id = id;
    this.text = text;
    this.time = time;
    this.likes = likes;
  }

  public User getUser() {
    return user;
  }

  public long getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public String getTime() {
    return time;
  }

  public int getLikes() {
    return likes;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Post post = (Post) o;
    return id == post.id && likes == post.likes && user.equals(post.user) && text.equals(post.text)
        && time.equals(post.time);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, id, text, time, likes);
  }
}
