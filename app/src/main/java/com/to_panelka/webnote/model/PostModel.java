package com.to_panelka.webnote.model;

import java.sql.Date;
import java.sql.Time;

public class PostModel {

    String id;
    String date;
    String description;

    public PostModel(String id, String date, String description) {
        this.id = id;
        this.date = date;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDate() {return date;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
