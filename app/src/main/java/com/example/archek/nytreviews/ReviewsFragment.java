package com.example.archek.nytreviews;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archek.nytreviews.model.reviews.ReviewsResponse;
import com.example.archek.nytreviews.model.reviews.ReviewsResult;
import com.example.archek.nytreviews.net.NYTService;
import com.example.archek.nytreviews.net.RestApi;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewsFragment extends Fragment implements RevAdapter.Callback {
    DatePicker datePicker; //Instal variables
    TextView dateVision;
    Button btnDateChange;
    String today;
    RecyclerView rvReviews;
    RevAdapter adapter = new RevAdapter( this );
    Call <ReviewsResponse> call;
    private final NYTService service = RestApi.createService( NYTService.class );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_reviews, container, false );

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        dateVision = view.findViewById( R.id.tvDate );//Инициализируем переменные/initiate variables
        setToday();
        dateVision.setText( today );
        datePicker = view.findViewById( R.id.dpDatePicker );
        btnDateChange = view.findViewById( R.id.btnDateChange );
        btnDateChange.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickDateChange( v );
            }
        } );

        setupRecyclerView( view );
        loadReviews();
        dateVision.setOnClickListener( new View.OnClickListener() {//Кнопка выбора даты(по умолчанию сегодня)
            @Override                                               // Button choosing date(default - today)
            public void onClick(View v) {
                datePicker.setVisibility( View.VISIBLE );
                rvReviews.setVisibility( View.INVISIBLE );
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
    public void buttonClickDateChange (View view) {  //Обработка выбора даты/Working on date choose
        String date;
        int dayOfMonth = datePicker.getDayOfMonth();
        int monthOfYear = datePicker.getMonth();
        int year = datePicker.getYear();
        String day;
        if (dayOfMonth < 10) day = "0" + dayOfMonth;
        else day = String.valueOf(dayOfMonth);
        monthOfYear++;
        String month;
        if (monthOfYear < 10) month = "0" + monthOfYear;
        else month = String.valueOf(monthOfYear);
        date = day + "/" + month + "/" + year;
        dateVision.setText( date );
        datePicker.setVisibility( View.GONE );
        rvReviews.setVisibility( View.VISIBLE );
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
    @Override
    public void onReviewClick(ReviewsResult reviewsResult) {
        Intent intent = Article.makeIntent( getContext(), reviewsResult );
        startActivity( intent );

    }
}
