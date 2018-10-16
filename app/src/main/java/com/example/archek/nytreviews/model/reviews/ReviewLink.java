package com.example.archek.nytreviews.model.reviews;

import com.google.gson.annotations.SerializedName;

public class ReviewLink {//pojo models for RESTing work. Initalize all patametrs,
    // but did't use all of them. Still set all, for further work, i hope so:)
    private String type;
    private String url;
    @SerializedName("suggested_link_text")
    private String suggestedLinkText;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSuggestedLinkText() {
        return suggestedLinkText;
    }

    public void setSuggestedLinkText(String suggestedLinkText) {
        this.suggestedLinkText = suggestedLinkText;
    }
}
