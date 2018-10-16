package com.example.archek.nytreviews.model.critics;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CriticsResponse {//pojo models for RESTing work. Initalize all patametrs,
    // but did't use all of them. Still set all, for further work, i hope so:)

        private String status;
        private String copyright;
        @SerializedName("num_results")
        private Integer numResults;
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


