package com.example.archek.nytreviews.reviews;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archek.nytreviews.R;
import com.example.archek.nytreviews.model.reviews.ReviewsResponse;
import com.example.archek.nytreviews.net.NYTService;
import com.example.archek.nytreviews.net.RestApi;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewsFragment extends Fragment {
    private TextView dateVision;//instal variables/components
    private ImageView ivSearch;
    private String today;
    private RecyclerView rvReviews;
    private ReviewAdapter adapter = new ReviewAdapter(  );
    private Call <ReviewsResponse> call;
    private final NYTService service = RestApi.createService( NYTService.class );
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_reviews, container, false );
        setupRecyclerView( rootView );

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        dateVision = view.findViewById( R.id.tvDate );
        ivSearch = view.findViewById( R.id.ivSearch );
        setToday();//set actual date
        dateVision.setText( today );//set date
        loadReviews();//load list reviews

        ivSearch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {//instal listener on search
                EditText etSearch = view.findViewById( R.id.etSearch );
                etSearch.setVisibility( View.VISIBLE );
                searchReviews(etSearch);
            }
        } );

        dateVision.setOnClickListener( new View.OnClickListener() {//instal listener on dateVision(date updateFilter)
            @Override
            public void onClick(View v) {
                filterUpdate();
            }
        } );
    }

    private void loadReviews() {//method for loading reviews from api to adapter
        call = service.getReviews();
        call.enqueue( new Callback <ReviewsResponse>() {
            @Override
            public void onResponse(Call <ReviewsResponse> call, Response <ReviewsResponse> response) {
                ReviewsResponse reviewsResponse = response.body();
                adapter.replaceAll( reviewsResponse.getResults() );
            }

            @Override
            public void onFailure(Call <ReviewsResponse> call, Throwable t) {
                if (call.isCanceled()) {
                    Toast.makeText( getContext(), R.string.error, Toast.LENGTH_SHORT).show();
                }
            }
        } );
    }

    private void filterUpdate() {//method for get date filtred reviews data( upped  earliest articles)
        call = service.getReviews();
        call.enqueue( new Callback <ReviewsResponse>() {
            @Override
            public void onResponse(Call <ReviewsResponse> call, Response <ReviewsResponse> response) {
                ReviewsResponse reviewsResponse = response.body();
                adapter.replaceForTime( reviewsResponse.getResults() );
            }

            @Override
            public void onFailure(Call <ReviewsResponse> call, Throwable t) {
                if (call.isCanceled()) {
                    Toast.makeText( getContext(), R.string.error, Toast.LENGTH_SHORT ).show();
                }
            }
        } );

    }


    public void setToday() { // Instal todat date in simple format
        @SuppressLint("SimpleDateFormat")
                SimpleDateFormat dateFormat = new SimpleDateFormat( "dd/MM/yyyy" );
        Date dater = new Date();
        today = dateFormat.format( dater );
    }

    private void setupRecyclerView(View view) {//setup recyclerview and layouts stuff
        rvReviews = view.findViewById(R.id.rvReviews);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvReviews.setLayoutManager(layoutManager);
        rvReviews.setAdapter(adapter);
    }

    public void searchReviews (EditText editText){// method search inputed simbols in data and complicate reviews
                                                  // containing such simbols
        editText.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(final Editable searchBody) {
                handler.removeCallbacksAndMessages( null );
                handler.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        if(searchBody.toString().isEmpty()){
                            return;
                        }
                        if (call != null){
                            call.cancel();
                        }
                        call = service.getSearchReviews(searchBody.toString());
                        //noinspection ConstantConditions
                        call.enqueue(new Callback <ReviewsResponse>() {
                            @Override
                            public void onResponse(Call <ReviewsResponse> call, Response<ReviewsResponse> response) {
                                    adapter.replaceSearch( searchBody.toString().toLowerCase(), response.body() );
                            }

                            @Override
                            public void onFailure(Call <ReviewsResponse> call, Throwable t) {
                                if(!call.isCanceled()){
                                    Toast.makeText( getContext(),R.string.error, Toast.LENGTH_SHORT ).show();
                                }
                            }
                        } );
                    }

                },400 );
            }
        } );
    }


}
