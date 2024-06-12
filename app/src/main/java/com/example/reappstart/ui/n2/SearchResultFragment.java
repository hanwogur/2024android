package com.example.reappstart.ui.n2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.reappstart.R;
import com.example.reappstart.ui.n1.DataAdapter;
import com.example.reappstart.database.CookRecipeResponse;
import com.example.reappstart.ui.n1.DetailActivity;

import java.util.List;

public class SearchResultFragment extends Fragment {
    private SearchResultViewModel searchResultViewModel;
    private RecyclerView recyclerView;
    private DataAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("SearchResultFragment", "onCreateView called");
        View root = inflater.inflate(R.layout.search_result, container, false);

        try {
            ImageButton backButton = root.findViewById(R.id.end);
            recyclerView = root.findViewById(R.id.recycler_view2);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            Log.d("SearchResultFragment", "RecyclerView initialized");

            searchResultViewModel = new ViewModelProvider(this).get(SearchResultViewModel.class);
            Log.d("SearchResultFragment", "ViewModelProvider initialized");

            if (getArguments() != null) {
                String query = getArguments().getString("query");
                Log.d("SearchResultFragment", "Received query: " + query);
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
                    Log.d("SearchResultFragment", "Search results size: " + recipes.size());
                    adapter = new DataAdapter(recipes, this::onItemClick);
                    recyclerView.setAdapter(adapter);
                    Log.d("SearchResultFragment", "RecyclerView adapter set");
                } else {
                    Log.e("SearchResultFragment", "Search results are null");
                }
            });
        } catch (Exception e) {
            Log.e("SearchResultFragment", "Error in onCreateView", e);
        }

        return root;
    }


    private void onItemClick(CookRecipeResponse.RecipeRow item) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("tit", item.getRCP_NM());
        intent.putExtra("category", item.getRCP_PAT2());
        intent.putExtra("image", item.getATT_FILE_NO_MAIN());
        intent.putExtra("RCP_SEQ", item.getRCP_SEQ());
        startActivity(intent);
    }
}
