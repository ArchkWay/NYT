package com.example.archek.nytreviews.critics;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.archek.nytreviews.R;
import com.example.archek.nytreviews.model.critics.CriticResults;
import com.example.archek.nytreviews.model.critics.CriticsResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CriticAdapter extends RecyclerView.Adapter<CriticAdapter.ViewHolder> {

    private List<CriticResults> results = new ArrayList<>();
    private final Callback callback;

    public CriticAdapter(Callback callback) { //Конструктор с колбэком
        this.callback = callback;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate( R.layout.item_critics, parent, false);
        final ViewHolder holder = new ViewHolder( itemView );
        itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CriticResults critic = results.get(holder.getAdapterPosition());
                callback.onCriticClick( critic );
            }
        } );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CriticResults criticResults = results.get(position);
        holder.tvTitle.setText( criticResults.getDisplayName() );
        try {
             Picasso.get().load( criticResults.getMultimedia().getResource().getSrc() ).into( holder.ivPhoto );
        }
       catch (Exception o){}

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    @Override
    final public int getItemViewType(int position) {
        return position;
    }

//    final public int getViewTypeCount() {
//        return 1;
//    }




    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPhoto;
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivPhotoCritic);
            tvTitle = itemView.findViewById(R.id.tvNameCritic);
        }


    }

    public void replaceAll(CriticsResponse criticsResponse) {
        results.clear();
        results.addAll(criticsResponse.getResults());
        notifyDataSetChanged();
    }


    public void replaceSearch(String searchBody, CriticsResponse criticsResponse){

        results.clear();
        ArrayList<CriticResults> tempResults = new ArrayList <>(  );
        tempResults.addAll( criticsResponse.getResults());
        for(int i = 0; i < tempResults.size(); i++){
            if (tempResults.get( i ).getDisplayName().toLowerCase().contains( searchBody ) ) {
                results.add( tempResults.get( i ) );
            }
        }

        notifyDataSetChanged();
    }
    public interface Callback{
        void onCriticClick(CriticResults critic);

    }
}




