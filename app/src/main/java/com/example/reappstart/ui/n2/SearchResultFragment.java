package com.example.reappstart.ui.n2;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.reappstart.R;
import com.example.reappstart.ui.n1.DataAdapter;
import com.example.reappstart.database.CookRecipeResponse;
import java.util.List;

public class SearchResultFragment extends Fragment {
    private SearchResultViewModel searchResultViewModel;
    private RecyclerView recyclerView;
    private DataAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.search_result, container, false);

        Button backButton = root.findViewById(R.id.end);

        recyclerView = root.findViewById(R.id.recycler_view2);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));



        searchResultViewModel = new ViewModelProvider(this).get(SearchResultViewModel.class);
        if (getArguments() != null) {
            String query = getArguments().getString("query");
            if (query != null && !query.isEmpty()) {
                searchResultViewModel.searchRecipes(query);
            }
        }

        EditText searchBar = root.findViewById(R.id.search_bar);
        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                String query = searchBar.getText().toString().trim();
                if (!query.isEmpty()) {
                    searchResultViewModel.searchRecipes(query);
                }
                return true;
            }
            return false;
        });

        backButton.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });


        searchResultViewModel.getSearchResults().observe(getViewLifecycleOwner(), recipes -> {
            if (recipes != null) {
                adapter = new DataAdapter(recipes, this::onItemClick);
                recyclerView.setAdapter(adapter);
            } else {
                // Handle the case where search results are null
                // Show an error message or empty state to the user
            }
        });

        return root;
    }

    private void onItemClick(CookRecipeResponse.RecipeRow item) {
        // 아이템 클릭 시 동작을 정의합니다.
    }
}
