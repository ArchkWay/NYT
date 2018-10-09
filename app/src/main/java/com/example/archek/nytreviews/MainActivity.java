package com.example.archek.nytreviews;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        if (savedInstanceState == null) {
            replaceFragment(new ReviewsFragment());
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu, menu );
        for(int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem( i );
            SpannableString spanString = new SpannableString( menu.getItem( i ).getTitle().toString() );
            int end = spanString.length();
            spanString.setSpan( new RelativeSizeSpan( 2f ), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
            item.setTitle( spanString );
        }
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item)
    {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_reviews:
                setTitle( "Reviews" );
                Fragment reviewsFragment = new ReviewsFragment();
                replaceFragment( reviewsFragment );
                return true;
            case R.id.menu_critics:
                setTitle( "Critics" );
                Fragment criticsFragment = new CriticsFragment();
                replaceFragment(criticsFragment);
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
