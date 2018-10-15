package com.example.archek.nytreviews.reviews;

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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<ReviewsResult> reviewsResults = new LinkedList<>();


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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReviewsResult reviewsResult = reviewsResults.get(position);
        Picasso.get().load(reviewsResult.getMultimedia().getSrc()).into(holder.ivPhoto);
        holder.tvTitle.setText(reviewsResult.getDisplayTitle());
        holder.tvShortDesc.setText(reviewsResult.getSummaryShort());
        holder.tvNameAuthor.setText( reviewsResult.getByline() );
        holder.tvDateReview.setText( reviewsResult.getDateUpdated().toString() );
    }

    @Override
    public int getItemCount() {
        return reviewsResults.size();
    }


    public int getItemViewType(int position) {
        return 0;
    }

    public void replaceAll(List<ReviewsResult> reviewsToReplace) {
        reviewsResults.clear();
        reviewsResults.addAll(reviewsToReplace);
        notifyDataSetChanged();
    }

    public void replaceSearch(String searchBody, ReviewsResponse searchResponse){
        reviewsResults.clear();
        LinkedList<ReviewsResult> tempResults = new LinkedList <>(  );
        tempResults.addAll( searchResponse.getResults() );

        for (int i = 0; i < tempResults.size(); i++) {
            if (tempResults.get( i ).getDisplayTitle().toLowerCase().contains( searchBody ) || tempResults.get( i ).getByline().toLowerCase().contains( searchBody ) || tempResults.get( i ).getSummaryShort().toLowerCase().contains( searchBody )) {
                reviewsResults.add( tempResults.get( i ) );
            }
            notifyDataSetChanged();
        }
    }

    public void replaceForTime(List<ReviewsResult> reviewsToReplace) {
        reviewsResults.clear();
        LinkedList<String > timeList = new LinkedList <>(  );
        for(int i = 0; i < reviewsToReplace.size(); i++){
            timeList.add( reviewsToReplace.get( i ).getDateUpdated() );
        }
        Collections.sort(timeList);
        List<ReviewsResult> sortedResults = new LinkedList <>(  );
        for(int i = 0; i < timeList.size(); i++){
            for(int j = 0; j < reviewsToReplace.size(); j++){
                if(reviewsToReplace.get(j).getDateUpdated().contains( timeList.get(i) )){
                    sortedResults.add( reviewsToReplace.get(j) );
                }
            }
        }
        reviewsResults.addAll( sortedResults );

        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPhoto;
        TextView tvTitle;
        TextView tvShortDesc;
        TextView tvNameAuthor;
        TextView tvDateReview;
        WebView wbArticle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivPhotoTitle);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvShortDesc = itemView.findViewById(R.id.tvShortDescript);
            tvNameAuthor = itemView.findViewById( R.id.tvNameAuthor );
            tvDateReview = itemView.findViewById( R.id.tvDateReview );
            wbArticle = itemView.findViewById( R.id.wbArticleUrl );
        }
    }
}
