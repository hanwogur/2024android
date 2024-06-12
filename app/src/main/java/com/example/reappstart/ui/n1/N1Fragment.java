package com.example.reappstart.ui.n1;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reappstart.DetailActivity;
import com.example.reappstart.R;
import com.example.reappstart.database.Retrofit_interface;
import com.example.reappstart.database.databaseManager;
import com.example.reappstart.database.retrofit_client;
import com.example.reappstart.databinding.FragmentN1Binding;
import com.example.reappstart.database.CookRecipeResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class N1Fragment extends Fragment {
    private FragmentN1Binding binding;
    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private Animation rotation;
    private databaseManager db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        N1ViewModel n1ViewModel = new ViewModelProvider(this).get(N1ViewModel.class);

        binding = FragmentN1Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = new databaseManager(getActivity());
        fetchDataAndStore();

        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new DataAdapter(new ArrayList<>(), this::onItemClick);
        recyclerView.setAdapter(adapter);

        n1ViewModel.getRecipes().observe(getViewLifecycleOwner(), recipes -> {
            Log.d(TAG, "Observed recipes: " + recipes.size());
            adapter.setData(recipes);
        });

        return root;
    }

    private void onItemClick(CookRecipeResponse.RecipeRow item) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("tit", item.getRCP_NM());
        intent.putExtra("cate", item.getRCP_PAT2());
        intent.putExtra("image", item.getATT_FILE_NO_MAIN());

        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void fetchDataAndStore() {
        startLoadingAnimation();

        Retrofit_interface service = retrofit_client.getApiService();
        Call<CookRecipeResponse> call = service.stock_api_get("1", "10");
        call.enqueue(new Callback<CookRecipeResponse>() {
            @Override
            public void onResponse(Call<CookRecipeResponse> call, Response<CookRecipeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CookRecipeResponse recipeResponse = response.body();
                    //데이터 베이스에 데이터를 삽입
                    ArrayList<CookRecipeResponse> recipeList = new ArrayList<>();
                    recipeList.add(recipeResponse);
                    db.insertData(recipeList);
                } else {
                    // 네트워크 요청 실패 처리, UI 업데이트는 실시간 상황에 따라 다를 수 있다.
                }
                stopLoadingAnimation();
            }

            @Override
            public void onFailure(Call<CookRecipeResponse> call, Throwable t) {
                // 네트워크 요청 실패 처리
                stopLoadingAnimation();
            }
        });
    }

    private void startLoadingAnimation() {
        rotation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anime);
        binding.iv.startAnimation(rotation);
    }

    private void stopLoadingAnimation() {
        rotation.cancel(); // 애니메이션 취소
        binding.iv.clearAnimation();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
