package com.example.archek.nytreviews.model.critics;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CriticMultimedia implements Serializable {
    @SerializedName("resource")
    private CriticResource resource;



    public CriticResource getResource() {
        return resource;
    }

    public void setResource(CriticResource resource) {
        this.resource = resource;
    }
}
