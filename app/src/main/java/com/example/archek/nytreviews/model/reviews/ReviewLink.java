package com.example.archek.nytreviews.model.reviews;

import com.google.gson.annotations.SerializedName;

public class ReviewLink {
    @SerializedName("type")
    private String type;
    @SerializedName("url")
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
