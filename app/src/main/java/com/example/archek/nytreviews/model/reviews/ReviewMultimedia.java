package com.example.archek.nytreviews.model.reviews;


public class ReviewMultimedia {//pojo models for RESTing work. Initalize all patametrs,
    // but did't use all of them. Still set all, for further work, i hope so:)
    private String type;
    private String src;
    private Integer width;
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
