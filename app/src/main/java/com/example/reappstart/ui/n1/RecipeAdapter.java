package com.example.reappstart.ui.n1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.reappstart.R;

import java.util.List;
import java.util.Map;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Map<String, String>> recipeSteps;
    private Context context;

    public RecipeAdapter(Context context, List<Map<String, String>> recipeSteps) {
        this.context = context;
        this.recipeSteps = recipeSteps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map<String, String> step = recipeSteps.get(position);
        holder.manualStep.setText(step.get("manual"));
        String manualImgUrl = step.get("manualImgUrl");
        if (manualImgUrl != null && !manualImgUrl.isEmpty()) {
            holder.manualImage.setVisibility(View.VISIBLE);
            Glide.with(context).load(manualImgUrl).into(holder.manualImage);
        } else {
            holder.manualImage.setVisibility(View.GONE);
        }
        Log.d("RecipeAdapter", "Step " + (position + 1) + ": manual=" + step.get("manual") + ", manualImgUrl=" + manualImgUrl); // 추가된 로그
    }

    @Override
    public int getItemCount() {
        return recipeSteps.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView manualStep;
        ImageView manualImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            manualStep = itemView.findViewById(R.id.manual_step);
            manualImage = itemView.findViewById(R.id.manual_image);
        }
    }
}
