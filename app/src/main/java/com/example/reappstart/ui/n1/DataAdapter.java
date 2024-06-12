package com.example.reappstart.ui.n1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.reappstart.R;
import com.example.reappstart.database.CookRecipeResponse;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<CookRecipeResponse.RecipeRow> dataList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(CookRecipeResponse.RecipeRow item);
    }

    public DataAdapter(List<CookRecipeResponse.RecipeRow> dataList, OnItemClickListener listener) {
        this.dataList = dataList;
        this.mListener = listener;
    }
    public void setData(List<CookRecipeResponse.RecipeRow> newData) {
        this.dataList = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CookRecipeResponse.RecipeRow recipe = dataList.get(position);
        holder.textViewTitle.setText(recipe.getRCP_NM()); // 메뉴 이름 설정
        holder.category.setText(recipe.getRCP_PAT2()); // 카테고리
        Glide.with(holder.itemView.getContext())
                .load(recipe.getATT_FILE_NO_MAIN())
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            if (position != RecyclerView.NO_POSITION && mListener != null) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class );
                intent.putExtra("tit", recipe.getRCP_NM());
                intent.putExtra("image", recipe.getATT_FILE_NO_MAIN());
                intent.putExtra("category", recipe.getRCP_PAT2());
                v.getContext().startActivity(intent);
                mListener.onItemClick(recipe);
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, category;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            imageView = itemView.findViewById(R.id.image_view);
            category = itemView.findViewById(R.id.category);
        }
    }
}
