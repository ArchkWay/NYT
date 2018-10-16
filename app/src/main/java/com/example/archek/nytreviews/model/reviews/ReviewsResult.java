package com.example.archek.nytreviews.model.reviews;

import com.google.gson.annotations.SerializedName;

public class ReviewsResult {//pojo models for RESTing work. Initalize all patametrs,
    // but did't use all of them. Still set all, for further work, i hope so:)
    @SerializedName("display_title")
    private String displayTitle;
    @SerializedName("mpaa_rating")
    private String mpaaRating;
    @SerializedName("critics_pick")
    private Integer criticsPick;
    private String byline;
    private String headline;
    @SerializedName("summary_short")
    private String summaryShort;
    @SerializedName("publication_date")
    private String publicationDate;
    @SerializedName("opening_date")
    private String openingDate;
    @SerializedName("date_updated")
    private String dateUpdated;
    private ReviewLink link;
    private ReviewMultimedia multimedia;

    public String getDisplayTitle() {
        return displayTitle;
    }

    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public Integer getCriticsPick() {
        return criticsPick;
    }

    public void setCriticsPick(Integer criticsPick) {
        this.criticsPick = criticsPick;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSummaryShort() {
        return summaryShort;
    }

    public void setSummaryShort(String summaryShort) {
        this.summaryShort = summaryShort;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public ReviewLink getLink() {
        return link;
    }

    public void setLink(ReviewLink link) {
        this.link = link;
    }

    public ReviewMultimedia getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(ReviewMultimedia multimedia) {
        this.multimedia = multimedia;
    }
}
