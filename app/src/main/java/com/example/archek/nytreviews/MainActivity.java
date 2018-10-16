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
    TextView tvTitleFragment;
    ConstraintLayout clMain;
    private Toolbar toolbar;
    TextView tvReviewTitle;
    TextView tvCriticTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setTitle( "" );
        setContentView( R.layout.activity_main );
        toolbar = findViewById( R.id.toolbar );
        clMain = findViewById( R.id.clMain );
        tvReviewTitle = findViewById( R.id.tvReviewTitle );
        tvCriticTitle = findViewById( R.id.tvCriticTitle );
        tvReviewTitle.setOnClickListener( listener );
        tvCriticTitle.setOnClickListener( listener );
        setSupportActionBar( toolbar );

        if (savedInstanceState == null) {
            replaceFragment( new ReviewsFragment() );
        }
        tvTitleFragment = findViewById( R.id.tvTitleFragment );
    }


//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate( R.menu.menu, menu );
//        return true;
//    }

//    public boolean onOptionsItemSelected (MenuItem item)
//    {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.menu_reviews:
//                tvTitleFragment.setText( "Reviews" );
//                Fragment reviewsFragment = new ReviewsFragment();
//                replaceFragment( reviewsFragment );
//                clMain = findViewById( R.id.clMain );
//                clMain.setBackground( getResources().getDrawable( R.drawable.review_back));
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    Window window = getWindow();
//                    window.setStatusBarColor( getResources().getColor( R.color.colorPrimary ) );
//                }
//                toolbar.setBackgroundColor( getResources().getColor( R.color.colorPrimary ) );
//                return true;
//            case R.id.menu_critics:
//                tvTitleFragment.setText( "Critics" );
//                Fragment criticsFragment = new CriticsFragment();
//                replaceFragment(criticsFragment);
//                clMain.setBackground( getResources().getDrawable( R.drawable.critic_back));
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    Window window = getWindow();
//                    window.setStatusBarColor( getResources().getColor( R.color.colorAccent ));
//                }
//                toolbar.setBackgroundColor( getResources().getColor( R.color.colorAccent ) );
//                return true;
//            default:
//                return super.onOptionsItemSelected( item );
//        }
//    }

    private void replaceFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace( R.id.fragmentContainer, fragment )
                    .commit();
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {

                case R.id.tvReviewTitle:
                    tvTitleFragment.setText( "Reviews" );
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
                    tvTitleFragment.setText( "Critics" );
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