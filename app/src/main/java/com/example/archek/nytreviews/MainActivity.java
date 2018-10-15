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
import android.view.Window;
import android.widget.TextView;

import com.example.archek.nytreviews.critics.CriticAdapter;
import com.example.archek.nytreviews.critics.CriticsFragment;
import com.example.archek.nytreviews.model.critics.CriticResults;
import com.example.archek.nytreviews.reviews.ReviewAdapter;
import com.example.archek.nytreviews.reviews.ReviewsFragment;

public class MainActivity extends AppCompatActivity  {
    TextView tvTitleFragment;
    ConstraintLayout clMain;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        clMain = findViewById( R.id.clMain );
        if (savedInstanceState == null) {
            replaceFragment(new ReviewsFragment());
        }
        tvTitleFragment = findViewById( R.id.tvTitleFragment );

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu, menu );
//        for(int i = 0; i < menu.size(); i++) {
//            MenuItem item = menu.getItem( i );
//            SpannableString spanString = new SpannableString( menu.getItem( i ).getTitle().toString() );
//            int end = spanString.length();
//            spanString.setSpan( new RelativeSizeSpan( 2f ), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
//            item.setTitle( spanString );
//        }
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item)
    {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_reviews:
                tvTitleFragment.setText( "Reviews" );
                Fragment reviewsFragment = new ReviewsFragment();
                replaceFragment( reviewsFragment );
                clMain.setBackground( ContextCompat.getDrawable(this, R.drawable.review_back));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.setStatusBarColor( getResources().getColor( R.color.colorPrimary ) );
                }
                toolbar.setBackgroundColor( getResources().getColor( R.color.colorPrimary ) );
                return true;
            case R.id.menu_critics:
                tvTitleFragment.setText( "Critics" );
                Fragment criticsFragment = new CriticsFragment();
                replaceFragment(criticsFragment);
                clMain.setBackground( ContextCompat.getDrawable(this, R.drawable.critic_back));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.setStatusBarColor( getResources().getColor( R.color.colorAccent ));
                }
                toolbar.setBackgroundColor( getResources().getColor( R.color.colorAccent ) );
                return true;
            default:
                return super.onOptionsItemSelected( item );

        }

    }
    private void replaceFragment(Fragment fragment) {
        if(fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace( R.id.fragmentContainer, fragment )
                    .commit();
        }
    }



}
