package com.brandonhimes.whattocook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by brandon on 6/30/18.
 */

public class RecipeResultsAdapter extends RecyclerView.Adapter<RecipeResultsAdapter.RecipeViewHolder> {
    private Context mContext;
    private List<Recipe> mRecipeList;

    public RecipeResultsAdapter(Context context, List<Recipe> recipeList) {
        mContext = context;
        mRecipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.recipeTitle.setText(mRecipeList.get(position).getTitle());
        Picasso.get().load(mRecipeList.get(position).getImage_url()).fit().centerInside().into(holder.recipeImage);
        holder.recipeRating.setText(mContext.getString(R.string.rating, new DecimalFormat("#0.0").format(mRecipeList.get(position).getSocial_rank())));
        holder.recipeSource.setText(mContext.getString(R.string.source, mRecipeList.get(position).getPublisher()));
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        private TextView recipeTitle;
        private ImageView recipeImage;
        private TextView recipeRating;
        private TextView recipeSource;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeTitle = itemView.findViewById(R.id.title_textview);
            recipeImage = itemView.findViewById(R.id.recipe_imageview);
            recipeRating = itemView.findViewById(R.id.rating_textview);
            recipeSource = itemView.findViewById(R.id.source_textview);
        }
    }
}
