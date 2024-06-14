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
    private int selectedPosition = -1; // 선택된 아이템의 위치를 저장하는 변수

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

        // 배경색과 텍스트 색상 설정
        if (position == selectedPosition) {
            holder.itemView.setBackgroundColor(Color.parseColor("#D3D3D3")); // 연한 회색
            holder.mainCategoryText.setTextColor(Color.parseColor("#FF0000")); // 선택된 아이템의 텍스트 색상 (예: 빨간색)
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF")); // 흰색
            holder.mainCategoryText.setTextColor(Color.parseColor("#000000")); // 일반 아이템의 텍스트 색상 (예: 검은색)
        }

        holder.itemView.setOnClickListener(v -> {
            if (selectedPosition == position) {
                // 선택된 카테고리를 다시 클릭한 경우
                selectedPosition = -1; // 선택 해제
                notifyDataSetChanged(); // 전체 데이터 새로고침
                if (mListener != null) {
                    mListener.onItemClick(null); // null을 전달하여 리사이클러뷰를 초기화
                }
            } else {
                // 새로운 카테고리를 선택한 경우
                if (mListener != null) {
                    mListener.onItemClick(item);
                }
                int previousSelectedPosition = selectedPosition;
                selectedPosition = position;
                notifyItemChanged(previousSelectedPosition); // 이전 선택된 아이템 업데이트
                notifyItemChanged(selectedPosition); // 현재 선택된 아이템 업데이트
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