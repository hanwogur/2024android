package com.example.reappstart.ui.n1;

import android.graphics.Color;
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
    private OnItemClickListener mListener;
    private int selectedPosition = -1;

    public CategoryCardViewAdapter(List<CategoryCardItem> data, OnItemClickListener listener) {
        this.mData = data;
        this.mListener = listener;
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

        // 선택된 포지션인지 확인하고 배경색 변경
        if (position == selectedPosition) {
            holder.mainCategoryText.setTextColor(Color.parseColor("#FF0000"));
        } else {
            holder.mainCategoryText.setTextColor(Color.parseColor("#000000")); // 흰색
        }

        holder.itemView.setOnClickListener(v -> {
            // 이전 선택 해제
            notifyItemChanged(selectedPosition);
            // 새로운 선택
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(selectedPosition);

            if (mListener != null) {
                mListener.onItemClick(item);
            }
        });
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

    public interface OnItemClickListener {
        void onItemClick(CategoryCardItem item);
    }
}
