package com.example.archek.nytreviews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.archek.nytreviews.model.reviews.ReviewsResponse;
import com.example.archek.nytreviews.model.reviews.ReviewsResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RevAdapter extends RecyclerView.Adapter<RevAdapter.ViewHolder> {

    private List<ReviewsResult> reviewsResults = new ArrayList<>();
    private final Callback callback;

    public RevAdapter(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_reviews, parent, false);
        final ViewHolder holder = new ViewHolder( itemView );
        itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReviewsResult review  = reviewsResults.get(holder.getAdapterPosition());
                callback.onReviewClick( review );
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


    public void replaceAll(List<ReviewsResult> reviewsToReplace) {
        reviewsResults.clear();
        reviewsResults.addAll(reviewsToReplace);

        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPhoto;
        TextView tvTitle;
        TextView tvShortDesc;
        TextView tvNameAuthor;
        TextView tvDateReview;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivPhotoTitle);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvShortDesc = itemView.findViewById(R.id.tvShortDescript);
            tvNameAuthor = itemView.findViewById( R.id.tvNameAuthor );
            tvDateReview = itemView.findViewById( R.id.tvDateReview );
        }
    }
    public interface Callback{
        void onReviewClick(ReviewsResult review);
    }

}
