package com.example.archek.nytreviews.criticinfo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.archek.nytreviews.R;
import com.example.archek.nytreviews.model.reviews.ReviewsResponse;
import com.example.archek.nytreviews.model.reviews.ReviewsResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CriticInfoAdapter extends RecyclerView.Adapter<CriticInfoAdapter.ViewHolder> {

    private List<ReviewsResult> reviewsResults = new ArrayList<>();//main list for results to adapter


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate( R.layout.item_reviews, parent, false);
        final ViewHolder holder = new ViewHolder( itemView );
        itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReviewsResult reviewsResult  = reviewsResults.get(holder.getAdapterPosition());
                holder.wbArticle.loadUrl( reviewsResult.getLink().getUrl() );
            }
        } );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {//bind and load all views to holder
        ReviewsResult reviewsResult = reviewsResults.get(position);
        Picasso.get().load(reviewsResult.getMultimedia().getSrc()).into(holder.ivPhoto);
        holder.tvTitle.setText(reviewsResult.getDisplayTitle());
        holder.tvShortDesc.setText(reviewsResult.getSummaryShort());
        holder.tvNameAuthor.setText( reviewsResult.getByline() );
        holder.tvDateReview.setText( reviewsResult.getDateUpdated() );
    }



    public void replaceAllReviews(List<ReviewsResult> reviewsToReplace, String name) {//load searching by name reviews in main list
        reviewsResults.clear();
        for(int i = 0; i < reviewsToReplace.size(); i++){
            if(reviewsToReplace.get(i).getByline().toLowerCase().replaceAll("\\W", "").contains(name.toLowerCase().replaceAll("\\W", ""))){
                reviewsResults.add( reviewsToReplace.get( i ) );
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return reviewsResults.size();
    } //count all items

    public void replaceAllTest(List<ReviewsResult> reviewsToReplace) {//load all reviews in main list
        reviewsResults.clear();
        reviewsResults.addAll( reviewsToReplace );
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPhoto; //instal all views in holder
        private TextView tvTitle;
        private TextView tvShortDesc;
        private TextView tvNameAuthor;
        private TextView tvDateReview;
        private WebView wbArticle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivPhotoTitle);//initiate views
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvShortDesc = itemView.findViewById(R.id.tvShortDescript);
            tvNameAuthor = itemView.findViewById( R.id.tvNameAuthor );
            tvDateReview = itemView.findViewById( R.id.tvDateReview );
            wbArticle = itemView.findViewById( R.id.wbArticleUrl );
        }
    }
}
