package com.example.archek.nytreviews;

import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.archek.nytreviews.critics.CriticsFragment;
import com.example.archek.nytreviews.reviews.ReviewsFragment;

public class MainActivity extends AppCompatActivity {

    private TextView tvTitleFragment;//instal variables/components
    private ConstraintLayout clMain;
    private Toolbar toolbar;
    private TextView tvReviewTitle;
    private TextView tvCriticTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setTitle( "" );
        setContentView( R.layout.activity_main );//initiate variables/components
        toolbar = findViewById( R.id.toolbar );
        clMain = findViewById( R.id.clMain );
        tvReviewTitle = findViewById( R.id.tvReviewTitle );
        tvCriticTitle = findViewById( R.id.tvCriticTitle );
        tvTitleFragment = findViewById( R.id.tvTitleFragment );

        tvReviewTitle.setOnClickListener( listener );//instal listeners on TextViews for change fragments
        tvCriticTitle.setOnClickListener( listener );
        setSupportActionBar( toolbar );

        if (savedInstanceState == null) {//instal default fragment
            replaceFragment( new ReviewsFragment() );
        }
    }

    private void replaceFragment(Fragment fragment) {//method for instaling fragments
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace( R.id.fragmentContainer, fragment )
                    .commit();
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {//listener for change fragments/color themes
            int id = v.getId();
            switch (id) {

                case R.id.tvReviewTitle:
                    tvTitleFragment.setText( R.string.reviews );
                    Fragment reviewsFragment = new ReviewsFragment();
                    replaceFragment( reviewsFragment );
                    clMain = findViewById( R.id.clMain );
                    clMain.setBackground( getResources().getDrawable( R.drawable.review_back));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.setStatusBarColor( getResources().getColor( R.color.colorPrimary ) );
                    }
                    toolbar.setBackgroundColor( getResources().getColor( R.color.colorPrimary ) );
                    break;

                case R.id.tvCriticTitle:
                    tvTitleFragment.setText( R.string.critics );
                    Fragment criticsFragment = new CriticsFragment();
                    replaceFragment(criticsFragment);
                    clMain.setBackground( getResources().getDrawable( R.drawable.critic_back));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.setStatusBarColor( getResources().getColor( R.color.colorAccent ));
                    }
                    toolbar.setBackgroundColor( getResources().getColor( R.color.colorAccent ) );
                    break;

                default:
                    break;
            }

        }


    };

}