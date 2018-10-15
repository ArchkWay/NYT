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
        private List<CriticResults> results = null;

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

        public List<CriticResults> getResults() {
            return results;
        }

        public void setResults(List<CriticResults> results) {
            this.results = results;
        }

    }


