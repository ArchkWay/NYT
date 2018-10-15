package com.example.archek.nytreviews.model.critics;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CriticResults implements Serializable{
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("sort_name")
    private String sortName;
    @SerializedName("status")
    private String status;
    @SerializedName("bio")
    private Object bio;
    @SerializedName("seo_name")
    private String seoName;

    @SerializedName("multimedia")
    private CriticMultimedia multimedia = null;

    public CriticMultimedia getMultimedia() {
        return multimedia;
    }
    public void setMultimedia(CriticMultimedia multimedia) {
        this.multimedia = multimedia;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getBio() {
        return bio;
    }

    public void setBio(Object bio) {
        this.bio = bio;
    }

    public String getSeoName() {
        return seoName;
    }

    public void setSeoName(String seoName) {
        this.seoName = seoName;
    }
}
