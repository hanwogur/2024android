package com.example.reappstart.ui.n2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.reappstart.R;
import com.example.reappstart.ui.n1.DataAdapter;
import com.example.reappstart.database.CookRecipeResponse;

public class SearchResultFragment extends Fragment {
    private SearchResultViewModel searchResultViewModel;
    private RecyclerView recyclerView;
    private DataAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.search_result, container, false);

        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchResultViewModel = new ViewModelProvider(this).get(SearchResultViewModel.class);
        String query = getArguments().getString("query");
        searchResultViewModel.searchRecipes(query);

        searchResultViewModel.getSearchResults().observe(getViewLifecycleOwner(), recipes -> {
            adapter = new DataAdapter(recipes, this::onItemClick);
            recyclerView.setAdapter(adapter);
        });

        return root;
    }

    private void onItemClick(CookRecipeResponse.RecipeRow item) {
        // 아이템 클릭 시 동작을 정의합니다.
    }
}
