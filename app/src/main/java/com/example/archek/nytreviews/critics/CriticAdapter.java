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

import java.util.LinkedList;
import java.util.List;

public class CriticAdapter extends RecyclerView.Adapter<CriticAdapter.ViewHolder> {

    private List<CriticResults> critics = new LinkedList<>();//main list for results to adapter
    private final Callback callback; //callback for simply call CriticInfo activity

    public CriticAdapter(Callback callback) { //Конструктор с колбэком
        this.callback = callback;
    }//constructor


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate( R.layout.item_critics, parent, false);
        final ViewHolder holder = new ViewHolder( itemView );
        itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CriticResults critic = critics.get(holder.getAdapterPosition());//set on click listener, click inflate browser with corresponding url link
                callback.onCriticClick( critic );
            }
        } );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {//bind and load all views to holder
        CriticResults criticResults = critics.get(position);
        holder.tvTitle.setText( criticResults.getDisplayName() );
        try {
             Picasso.get().load( criticResults.getMultimedia().getResource().getSrc() ).into( holder.ivPhoto );
        }
        catch (Exception o){
            Picasso.get().load(R.drawable.noimage).into(holder.ivPhoto);
        }
    }

    @Override
    public int getItemCount() {
        return critics.size();
    }//count all items

    public void replaceAll(CriticsResponse criticsResponse) {//load all critics in main list
        critics.clear();
        critics.addAll(criticsResponse.getResults());
        notifyDataSetChanged();
    }

    public void replaceSearch(String searchBody, CriticsResponse criticsResponse){//load search critics
        critics.clear();
        LinkedList<CriticResults> tempResults = new LinkedList <>(  );
        tempResults.addAll( criticsResponse.getResults());
        for(int i = 0; i < tempResults.size(); i++){
            if (tempResults.get( i ).getDisplayName().toLowerCase().contains( searchBody ) ) {
                critics.add( tempResults.get( i ) );
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPhoto;//instal views in holder
        private TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivPhotoCritic);//initiate views
            tvTitle = itemView.findViewById(R.id.tvNameCritic);
        }
    }


    public interface Callback{
        void onCriticClick(CriticResults critic);
    }
}




