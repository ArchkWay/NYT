package com.example.archek.nytreviews.criticinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archek.nytreviews.R;
import com.example.archek.nytreviews.model.critics.CriticResults;
import com.example.archek.nytreviews.model.reviews.ReviewsResponse;
import com.example.archek.nytreviews.model.reviews.ReviewsResult;
import com.example.archek.nytreviews.net.NYTService;
import com.example.archek.nytreviews.net.RestApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriticInfoActivity extends AppCompatActivity {
    private Call<ReviewsResponse> call;
    private final NYTService service = RestApi.createService(NYTService.class);
    private CriticInfoAdapter adapter = new CriticInfoAdapter();
    private RecyclerView rvReviewsList;
    private static final String EXTRA_NAME = "EXTRA_NAME";//get extras from previous activity
    private static final String EXTRA_PHOTO = "EXTRA_PHOTO";
    private static final String EXTRA_BIO = "EXTRA_BIO";
    private static final String EXTRA_STATUS = "EXTRA_STATUS";

    public static Intent makeIntent(Context context, CriticResults critic) {//method for make and get data from pattern avtivity there
        try {
            return new Intent(context, CriticInfoActivity.class)
                    .putExtra(CriticInfoActivity.EXTRA_NAME, critic.getDisplayName())
                    .putExtra(CriticInfoActivity.EXTRA_PHOTO, critic.getMultimedia().getResource().getSrc())
                    .putExtra(CriticInfoActivity.EXTRA_BIO, critic.getBio().toString())
                    .putExtra(CriticInfoActivity.EXTRA_STATUS, critic.getStatus());
        }catch (Exception nullPhoto){
            try {
                return new Intent(context, CriticInfoActivity.class)
                        .putExtra(CriticInfoActivity.EXTRA_NAME, critic.getDisplayName())
                        .putExtra(CriticInfoActivity.EXTRA_BIO, critic.getBio().toString())
                        .putExtra(CriticInfoActivity.EXTRA_STATUS, critic.getStatus());
            }
            catch (Exception nullPhotoAndBio){
                return new Intent(context, CriticInfoActivity.class)
                        .putExtra(CriticInfoActivity.EXTRA_NAME, critic.getDisplayName())
                        .putExtra(CriticInfoActivity.EXTRA_STATUS, critic.getStatus());
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critic_info);

        rvReviewsList = findViewById(R.id.rvReviewsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvReviewsList.setLayoutManager(layoutManager);
        rvReviewsList.setAdapter(adapter);

        TextView tvName = findViewById(R.id.tvNameCriticInfo);//instal and initiate vews
        ImageView ivPhoto = findViewById(R.id.ivPhotoCriticInfo);
        TextView tvBio = findViewById(R.id.tvBio);
        TextView tvStatus = findViewById(R.id.tvStatusInfo);
        Button btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();//get intent data
        final String name = intent.getStringExtra(EXTRA_NAME);
        String urlPhoto = intent.getStringExtra(EXTRA_PHOTO);
        String bio = intent.getStringExtra(EXTRA_BIO);
        bio = Html.fromHtml(bio).toString();
        String status = intent.getStringExtra(EXTRA_STATUS);

        tvName.setText(name);//load all received data items(if them exist)in prepaired views
                                //
        Picasso.get().load(urlPhoto)
                .placeholder(R.drawable.noimage)
                .into(ivPhoto);

        if(!TextUtils.isEmpty(status)) {
            tvStatus.setText(status);
        }
        else tvStatus.setText(R.string.no_status);
        if(!TextUtils.isEmpty(bio)) {
            tvBio.setText(bio);
        }
        else tvBio.setText(R.string.no_bio);

        btnBack.setOnClickListener(new View.OnClickListener() {//back button - returning back
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        call = service.getReviews();//load reviews
        call.enqueue(new Callback<ReviewsResponse>() {
            @Override
            public void onResponse(Call <ReviewsResponse> call, Response<ReviewsResponse> response) {
                ReviewsResponse reviewsResponse = response.body();
                adapter.replaceAllReviews(reviewsResponse.getResults(), name);

            }

            @Override
            public void onFailure(Call <ReviewsResponse> call, Throwable t) {
                if (call.isCanceled()) {
                    Toast.makeText(CriticInfoActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
