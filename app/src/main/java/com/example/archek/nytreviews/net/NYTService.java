package com.example.archek.nytreviews.net;

import com.example.archek.nytreviews.model.critics.CriticsResponse;
import com.example.archek.nytreviews.model.reviews.ReviewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NYTService {//interface with all get quaries

    @GET("reviews/search.json?api-key=iRzizk9C7Q8FAiq5G0B01mfkipp0d3mP")
    Call<ReviewsResponse> getReviews();

    @GET("reviews/search.json?api-key=iRzizk9C7Q8FAiq5G0B01mfkipp0d3mP")
    Call<ReviewsResponse> getSearchReviews (@Query("names") String searchBody);

    @GET("critics/all.json?api-key=iRzizk9C7Q8FAiq5G0B01mfkipp0d3mP")
    Call<CriticsResponse> getCritics();

    @GET("critics/all.json?api-key=iRzizk9C7Q8FAiq5G0B01mfkipp0d3mP")
    Call<CriticsResponse> getSearchCritics (@Query("names") String searchBody);
}
