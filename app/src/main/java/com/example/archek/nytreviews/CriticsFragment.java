package com.example.archek.nytreviews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.archek.nytreviews.model.critics.CriticsResponse;
import com.example.archek.nytreviews.net.NYTService;
import com.example.archek.nytreviews.net.RestApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriticsFragment extends Fragment {

    Call <CriticsResponse> call;
    private final NYTService service = RestApi.createService( NYTService.class );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_critics, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        loadCritics();

    }

    private void loadCritics() {
        call = service.getCritics();
        call.enqueue( new Callback <CriticsResponse>() {
            @Override
            public void onResponse(Call <CriticsResponse> call, Response <CriticsResponse> response) {
                CriticsResponse criticsResponse = response.body();
            }

            @Override
            public void onFailure(Call <CriticsResponse> call, Throwable t) {
                if (call.isCanceled()) {
                    Toast.makeText( getContext(), R.string.error, Toast.LENGTH_SHORT ).show();
                }
            }
        } );

    }
}
