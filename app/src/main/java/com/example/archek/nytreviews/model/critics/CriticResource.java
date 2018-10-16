package com.example.archek.nytreviews.model.critics;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CriticResource {//pojo models for RESTing work. Initalize all patametrs,
    // but did't use all of them. Still set all, for further work, i hope so:)
    private String type;
    private String src;
    private Integer height;
    private Integer width;
    private String credit;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}
