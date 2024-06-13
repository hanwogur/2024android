package com.example.reappstart.ui.n1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.reappstart.R;

import java.util.List;

public class CategoryCardViewAdapter extends RecyclerView.Adapter<CategoryCardViewAdapter.ViewHolder> {

    private List<CategoryCardItem> mData;

    public CategoryCardViewAdapter(List<CategoryCardItem> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryCardItem item = mData.get(position);
        holder.mainCategoryText.setText(item.getCategoryText());
        holder.mainCategoryImage.setImageResource(item.getCategoryImageResource());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mainCategoryText;
        ImageView mainCategoryImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mainCategoryText = itemView.findViewById(R.id.main_category_text);
            mainCategoryImage = itemView.findViewById(R.id.main_category_image);
        }
    }
}
