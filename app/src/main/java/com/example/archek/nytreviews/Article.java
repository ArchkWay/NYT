package com.example.archek.nytreviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.archek.nytreviews.model.reviews.ReviewsResult;
import com.squareup.picasso.Picasso;

public class Article extends AppCompatActivity{

    private static final String EXTRA_URL = "EXTRA_URL";

    public static Intent makeIntent(Context context, ReviewsResult reviewsResult) {
        return new Intent( context, Article.class )
                .putExtra( Article.EXTRA_URL, reviewsResult.getLink().getUrl());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.article_layout );

        Intent intent = getIntent();
        String url = intent.getStringExtra( EXTRA_URL );//Перекладываем из экстра строк в локальные

//        "http://www.nytimes.com/2018/10/04/movies/studio-54-review.html";


        WebView wbArticle = findViewById( R.id.wbArticle );
        Button btnBack = findViewById( R.id.btnBack );

        wbArticle.loadUrl( url );

        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnBtn = new Intent(getApplicationContext(),//Возврат в Основное активити
                        MainActivity.class);                          //Comeback to MainActivity
                startActivity(returnBtn);
            }
        } );
    }
}
