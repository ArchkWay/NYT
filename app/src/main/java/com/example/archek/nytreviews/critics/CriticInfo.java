package com.example.archek.nytreviews.critics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.archek.nytreviews.MainActivity;
import com.example.archek.nytreviews.R;
import com.example.archek.nytreviews.model.critics.CriticResults;
import com.example.archek.nytreviews.model.reviews.ReviewsResult;
import com.squareup.picasso.Picasso;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CriticInfo extends AppCompatActivity {
    private static final String EXTRA_NAME = "EXTRA_NAME";
    private static final String EXTRA_PHOTO = "EXTRA_PHOTO";
    private static final String EXTRA_BIO = "EXTRA_BIO";
    private static final String EXTRA_STATUS = "EXTRA_STATUS";
    List<ReviewsResult> reviewsList = new ArrayList <>(  );



    public static Intent makeIntent(Context context, CriticResults critic) {
        try {
            return new Intent( context, CriticInfo.class )
                    .putExtra( CriticInfo.EXTRA_NAME, critic.getDisplayName() )
                    .putExtra( CriticInfo.EXTRA_PHOTO, critic.getMultimedia().getResource().getSrc() )
                    .putExtra( CriticInfo.EXTRA_BIO, critic.getBio().toString() )
                    .putExtra( CriticInfo.EXTRA_STATUS, critic.getStatus() );
        }catch (Exception o){
            try {
                return new Intent( context, CriticInfo.class )
                        .putExtra( CriticInfo.EXTRA_NAME, critic.getDisplayName() )
                        .putExtra( CriticInfo.EXTRA_BIO, critic.getBio().toString() )
                        .putExtra( CriticInfo.EXTRA_STATUS, critic.getStatus() );
            }
            catch (Exception p){
                return new Intent( context, CriticInfo.class )
                        .putExtra( CriticInfo.EXTRA_NAME, critic.getDisplayName() )
                        .putExtra( CriticInfo.EXTRA_BIO, critic.getBio().toString() );
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.info_critic );

        TextView tvName = findViewById( R.id.tvNameCriticInfo );
        ImageView ivPhoto = findViewById( R.id.ivPhotoCriticInfo );
        TextView tvBio = findViewById( R.id.tvBio );
        TextView tvStatus = findViewById( R.id.tvStatusInfo );
        Button btnBack = findViewById( R.id.btnBack );

        Intent intent = getIntent();
        String name = intent.getStringExtra( EXTRA_NAME );
        String urlPhoto = intent.getStringExtra( EXTRA_PHOTO );
        String bio = intent.getStringExtra( EXTRA_BIO );
        String status = intent.getStringExtra(EXTRA_STATUS);
        tvName.setText( name );
        try{
            if(!urlPhoto.equals( null )){
                Picasso.get().load( urlPhoto ).into( ivPhoto );
            }
        }
        catch (Exception n){}
        tvBio.setText( bio );
        tvStatus.setText( status );
        tvBio.setText( bio );

        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
    }
}
