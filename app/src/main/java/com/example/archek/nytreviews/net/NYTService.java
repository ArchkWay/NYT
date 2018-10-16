package com.example.archek.nytreviews.net;

import com.example.archek.nytreviews.model.critics.CriticsResponse;
import com.example.archek.nytreviews.model.reviews.ReviewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NYTService {//interface with all get quaries

    @GET("reviews/search.json?api-key=d858be33b19b4525abc9f15bbd1f30e3")
        Call<ReviewsResponse> getReviews();

    @GET("reviews/search.json?api-key=d858be33b19b4525abc9f15bbd1f30e3")
    Call<ReviewsResponse> getSearchReviews (@Query("names") String searchBody);

    @GET("critics/all.json?api-key=d858be33b19b4525abc9f15bbd1f30e3")
    Call<CriticsResponse> getCritics();

    @GET("critics/all.json?api-key=d858be33b19b4525abc9f15bbd1f30e3")
    Call<CriticsResponse> getSearchCritics (@Query("names") String searchBody);
}
