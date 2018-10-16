package com.example.archek.nytreviews.critics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.archek.nytreviews.R;
import com.example.archek.nytreviews.model.critics.CriticResults;
import com.example.archek.nytreviews.model.reviews.ReviewsResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CriticInfoActivity extends AppCompatActivity {
    private static final String EXTRA_NAME = "EXTRA_NAME";//get extras from previos activity
    private static final String EXTRA_PHOTO = "EXTRA_PHOTO";
    private static final String EXTRA_BIO = "EXTRA_BIO";
    private static final String EXTRA_STATUS = "EXTRA_STATUS";
        ArrayList<ReviewsResult> reviewsList;//there must be a list of reviews, but i didn't find it in received data,
                                        //i could find them manually from net, but not shure that implied for this task
    public static Intent makeIntent(Context context, CriticResults critic) {//method for make and get data from pattern avtivity there
        try {
            return new Intent( context, CriticInfoActivity.class )
                    .putExtra( CriticInfoActivity.EXTRA_NAME, critic.getDisplayName() )
                    .putExtra( CriticInfoActivity.EXTRA_PHOTO, critic.getMultimedia().getResource().getSrc() )
                    .putExtra( CriticInfoActivity.EXTRA_BIO, critic.getBio().toString() )
                    .putExtra( CriticInfoActivity.EXTRA_STATUS, critic.getStatus() );
        }catch (Exception nullPhoto){
            try {
                return new Intent( context, CriticInfoActivity.class )
                        .putExtra( CriticInfoActivity.EXTRA_NAME, critic.getDisplayName() )
                        .putExtra( CriticInfoActivity.EXTRA_BIO, critic.getBio().toString() )
                        .putExtra( CriticInfoActivity.EXTRA_STATUS, critic.getStatus() );
            }
            catch (Exception nullPhotoAndBio){
                return new Intent( context, CriticInfoActivity.class )
                        .putExtra( CriticInfoActivity.EXTRA_NAME, critic.getDisplayName() )
                        .putExtra( CriticInfoActivity.EXTRA_STATUS, critic.getStatus() );
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.info_critic );

        TextView tvName = findViewById( R.id.tvNameCriticInfo );//instal and initiate vews
        ImageView ivPhoto = findViewById( R.id.ivPhotoCriticInfo );
        TextView tvBio = findViewById( R.id.tvBio );
        TextView tvStatus = findViewById( R.id.tvStatusInfo );
        Button btnBack = findViewById( R.id.btnBack );

        Intent intent = getIntent();//get intent data
        String name = intent.getStringExtra( EXTRA_NAME );
        String urlPhoto = intent.getStringExtra( EXTRA_PHOTO );
        String bio = intent.getStringExtra( EXTRA_BIO );
        String status = intent.getStringExtra(EXTRA_STATUS);

        tvName.setText( name );//load all received data items(if them exist)in prepaired views
                                //
        Picasso.get().load(urlPhoto)
                .placeholder(R.drawable.noimage)
                .into(ivPhoto);

        if(!TextUtils.isEmpty(status)) {
            tvStatus.setText( status );
        }
        else tvStatus.setText( R.string.no_status );
        if(!TextUtils.isEmpty(bio)) {
            tvBio.setText( bio );
        }
        else tvBio.setText( R.string.no_bio );

        btnBack.setOnClickListener( new View.OnClickListener() {//back button - returning back
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
    }
}
