package com.example.archek.nytreviews.model.critics;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CriticsResponse {

        @SerializedName("status")
        private String status;
        @SerializedName("copyright")
        private String copyright;
        @SerializedName("num_results")
        private Integer numResults;
        @SerializedName("results")
        private List<CriticResult> results = null;

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

        public Integer getNumResults() {
            return numResults;
        }

        public void setNumResults(Integer numResults) {
            this.numResults = numResults;
        }

        public List<CriticResult> getResults() {
            return results;
        }

        public void setResults(List<CriticResult> results) {
            this.results = results;
        }

    }

    class CriticResult {

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
        private Object multimedia;

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

        public Object getMultimedia() {
            return multimedia;
        }

        public void setMultimedia(Object multimedia) {
            this.multimedia = multimedia;
        }

    }

