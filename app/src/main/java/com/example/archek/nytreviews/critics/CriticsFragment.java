package com.example.archek.nytreviews.critics;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.archek.nytreviews.MainActivity;
import com.example.archek.nytreviews.R;
import com.example.archek.nytreviews.model.critics.CriticResults;
import com.example.archek.nytreviews.model.critics.CriticsResponse;
import com.example.archek.nytreviews.model.reviews.ReviewsResponse;
import com.example.archek.nytreviews.net.NYTService;
import com.example.archek.nytreviews.net.RestApi;
import com.example.archek.nytreviews.reviews.ReviewAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriticsFragment extends Fragment implements CriticAdapter.Callback {


    CriticAdapter adapter = new CriticAdapter( this );
    Call <CriticsResponse> call;
    private final NYTService service = RestApi.createService( NYTService.class );
    RecyclerView rvCritics;
    Handler handler = new Handler(  );
    ImageView ivSearch;
//    final int spanCount = getResources().getInteger( R.integer.span_count );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_critics, container, false );

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        ivSearch = view.findViewById( R.id.ivSearchCritics );

        setupRecyclerView( view );
        loadCritics();
        ivSearch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etSearch;
                etSearch = view.findViewById( R.id.etSearchCritics );
                etSearch.setVisibility( View.VISIBLE );
                ivSearch.setVisibility( View.INVISIBLE );
                searchCritics( etSearch );
            }
        } );

    }

    private void loadCritics() {
        call = service.getCritics();
        call.enqueue( new Callback <CriticsResponse>() {
            @Override
            public void onResponse(Call <CriticsResponse> call, Response <CriticsResponse> response) {
                CriticsResponse criticsResponse = response.body();
                adapter.replaceAll( criticsResponse );
            }

            @Override
            public void onFailure(Call <CriticsResponse> call, Throwable t) {
                if (call.isCanceled()) {
                    Toast.makeText( getContext(), R.string.error, Toast.LENGTH_SHORT ).show();
                }
            }
        } );

    }
    private void setupRecyclerView(View view) {
        rvCritics = view.findViewById(R.id.rvCritics);
        GridLayoutManager lm = new GridLayoutManager( getContext(), 2 );//GridLayoutManager для сетчатых квадратных картинок
        rvCritics.setLayoutManager( lm );
        rvCritics.setAdapter( adapter );
    }

    public void searchCritics (EditText editText){//Метод для анализа вводимых данных, в поиск
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
                        call = service.getSearchCritics(searchBody.toString());
                        //noinspection ConstantConditions
                        call.enqueue( new Callback <CriticsResponse>() {
                            @Override
                            public void onResponse(Call <CriticsResponse> call, Response<CriticsResponse> response) {
                                if(response.body() != null) {
                                    //noinspection ConstantConditions
                                    adapter.replaceSearch( searchBody.toString().toLowerCase(), response.body() );
                                }
                            }

                            @Override
                            public void onFailure(Call <CriticsResponse> call, Throwable t) {
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

    @Override
    public void onCriticClick(CriticResults critic) {
        Intent intent = CriticInfo.makeIntent(getContext(),critic);
        startActivity( intent );

    }
}
