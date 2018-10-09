package com.example.archek.nytreviews.model.reviews;

import com.google.gson.annotations.SerializedName;

public class ReviewMultimedia {
    @SerializedName("type")
    private String type;
    @SerializedName("src")
    private String src;
    @SerializedName("width")
    private Integer width;
    @SerializedName("height")
    private Integer height;

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

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

}
