package com.example.archek.nytreviews.net;

import com.example.archek.nytreviews.model.critics.CriticsResponse;
import com.example.archek.nytreviews.model.reviews.ReviewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface NYTService {

    @GET("reviews/search.json?api-key=d858be33b19b4525abc9f15bbd1f30e3")
        Call<ReviewsResponse> getReviews();

    @GET("critics/all.json?api-key=d858be33b19b4525abc9f15bbd1f30e3")
    Call<CriticsResponse> getCritics();
}
