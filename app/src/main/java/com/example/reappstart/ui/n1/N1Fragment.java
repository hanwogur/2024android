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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reappstart.R;
import com.example.reappstart.database.Retrofit_interface;
import com.example.reappstart.database.databaseManager;
import com.example.reappstart.database.retrofit_client;
import com.example.reappstart.databinding.FragmentN1Binding;
import com.example.reappstart.database.CookRecipeResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class N1Fragment extends Fragment {
    private FragmentN1Binding binding;
    private RecyclerView recyclerView, categoryre;
    private DataAdapter adapter;
    private Animation rotation;
    private databaseManager db;
    private CategoryCardViewAdapter categoryadapter;
    private List<CategoryCardItem> mData;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        N1ViewModel n1ViewModel = new ViewModelProvider(this).get(N1ViewModel.class);

        binding = FragmentN1Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = new databaseManager(getActivity());
        fetchDataAndStore();

        //메인 카테고리 추가하려면 여기
        mData = new ArrayList<>();
        mData.add(new CategoryCardItem(R.drawable.rice, "밥"));
        mData.add(new CategoryCardItem(R.drawable.soup, "국&찌개"));
        mData.add(new CategoryCardItem(R.drawable.submenu, "반찬"));
        mData.add(new CategoryCardItem(R.drawable.dessert, "후식"));
        mData.add(new CategoryCardItem(R.drawable.onepum, "일품"));

        // 카테고리 리싸이클러뷰 설정
        categoryre = root.findViewById(R.id.category_re);
        categoryre.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        categoryadapter = new CategoryCardViewAdapter(mData);
        categoryre.setAdapter(categoryadapter);

        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new DataAdapter(new ArrayList<>(), this::onItemClick);
        recyclerView.setAdapter(adapter);

        n1ViewModel.getRecipes().observe(getViewLifecycleOwner(), recipes -> {
            Log.d(TAG, "Observed recipes: " + recipes.size());
            adapter.setData(recipes);
        });

        db = new databaseManager(getActivity());

        fetchDataAndStore();
        return root;
    }

    private void onItemClick(CookRecipeResponse.RecipeRow item) {
        Log.d("N1Fragment", "Item clicked: " + item.getRCP_SEQ()); // 로그 추가
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("tit", item.getRCP_NM());
        intent.putExtra("category", item.getRCP_PAT2());
        intent.putExtra("image", item.getATT_FILE_NO_MAIN());
        intent.putExtra("RCP_SEQ", item.getRCP_SEQ()); // RCP_SEQ 값을 추가
        Log.d("N1Fragment", "RCP_SEQ added to intent: " + item.getRCP_SEQ()); // 로그 추가
        startActivity(intent);
    }

    private void fetchDataAndStore() {

        Retrofit_interface service = retrofit_client.getApiService();
        Call<CookRecipeResponse> call = service.stock_api_get("1", "110");

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

            }

            @Override
            public void onFailure(Call<CookRecipeResponse> call, Throwable t) {
                // 네트워크 요청 실패 처리

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
