package com.example.archek.nytreviews.model.reviews;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewsResponse {

        @SerializedName("status")
        private String status;
        @SerializedName("copyright")
        private String copyright;
        @SerializedName("has_more")
        private Boolean hasMore;
        @SerializedName("num_results")
        private Integer numResults;
        @SerializedName("results")
        private List<ReviewsResult> results = null;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public Boolean getHasMore() {
            return hasMore;
        }

        public void setHasMore(Boolean hasMore) {
            this.hasMore = hasMore;
        }

        public Integer getNumResults() {
            return numResults;
        }

        public void setNumResults(Integer numResults) {
            this.numResults = numResults;
        }

        public List<ReviewsResult> getResults() {
            return results;
        }

        public void setResults(List<ReviewsResult> results) {
            this.results = results;
        }

    }


