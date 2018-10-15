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
    TextView dateVision;
    ImageView ivSearch;
    String today;
    RecyclerView rvReviews;
    ReviewAdapter adapter = new ReviewAdapter(  );
    Call <ReviewsResponse> call;
    private final NYTService service = RestApi.createService( NYTService.class );
    Handler handler = new Handler(  );
    SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_reviews, container, false );
        setupRecyclerView( rootView );
        refreshLayout = rootView.getRootView().findViewById(R.id.swrLayoutReview);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        final EditText etSearch = view.findViewById( R.id.etSearch );
        setToday();
        loadReviews();
        dateVision = view.findViewById( R.id.tvDate );
        ivSearch = view.findViewById( R.id.ivSearch );
        dateVision.setText( today );




        ivSearch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etSearch.setVisibility( View.VISIBLE );
                searchReviews( etSearch );
            }
        } );
        dateVision.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterUpdate();
            }
        } );

    }

    private void loadReviews() {
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
                    Toast.makeText( getContext(), R.string.error, Toast.LENGTH_SHORT ).show();
                }
            }
        } );

    }
    private void filterUpdate() {
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


    public void setToday() { //Ставим сегодняшнюю дату
        @SuppressLint("SimpleDateFormat") // Instal todat date
                SimpleDateFormat dateFormat = new SimpleDateFormat( "dd/MM/yyyy" );
        Date dater = new Date();
        today = dateFormat.format( dater );
    }
    private void setupRecyclerView(View view) {
        rvReviews = view.findViewById(R.id.rvReviews);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvReviews.setLayoutManager(layoutManager);
        rvReviews.setAdapter(adapter);
    }

    public void searchReviews (EditText editText){//Метод для анализа вводимых данных, в поиск
        //There is method for parsing input data to searching
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
                        call.enqueue( new Callback <ReviewsResponse>() {
                            @Override
                            public void onResponse(Call <ReviewsResponse> call, Response<ReviewsResponse> response) {
                                if(response.body() != null) {
                                    //noinspection ConstantConditions
                                    adapter.replaceSearch( searchBody.toString().toLowerCase(), response.body() );
                                }
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
    void refreshItems() {
        onItemsLoadComplete();
    }
    void onItemsLoadComplete() {
        refreshLayout.setRefreshing(false);
    }


}
