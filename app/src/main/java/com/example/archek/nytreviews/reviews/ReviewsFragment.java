package com.example.archek.nytreviews.reviews;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    DatePicker datePicker; //Instal variables
    TextView dateVision;
    Button btnDateChange;
    ImageView ivSearch;
    String today;
    RecyclerView rvReviews;
    ReviewAdapter adapter = new ReviewAdapter(  );
    Call <ReviewsResponse> call;
    private final NYTService service = RestApi.createService( NYTService.class );
    Handler handler = new Handler(  );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_reviews, container, false );

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        setToday();
        dateVision = view.findViewById( R.id.tvDate );//Инициализируем переменные/initiate variables
        ivSearch = view.findViewById( R.id.ivSearch );
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

        ivSearch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etSearch;
                etSearch = view.findViewById( R.id.etSearch );
                etSearch.setVisibility( View.VISIBLE );
                ivSearch.setVisibility( View.INVISIBLE );
                searchReviews( etSearch );
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


}
