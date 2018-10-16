package com.example.archek.nytreviews.model.critics;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CriticMultimedia implements Serializable {//pojo models for RESTing work. Initalize all patametrs,
                                                        // but did't use all of them. Still set all, for further work, i hope so:)
    private CriticResource resource;

    public CriticResource getResource() {
        return resource;
    }

    public void setResource(CriticResource resource) {
        this.resource = resource;
    }
}
