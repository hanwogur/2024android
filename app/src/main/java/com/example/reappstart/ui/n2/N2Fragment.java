package com.example.reappstart.ui.n2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reappstart.DetailActivity;
import com.example.reappstart.R;
import com.example.reappstart.database.CookRecipeResponse;
import com.example.reappstart.ui.n1.DataAdapter;

public class N2Fragment extends Fragment {
    private N2ViewModel n2ViewModel;
    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private EditText searchBar;
    private static final long SEARCH_DELAY_MS = 500; // 0.5초 지연 시간
    private Handler searchHandler = new Handler();
    private Runnable searchRunnable;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_n2, container, false);

        recyclerView = root.findViewById(R.id.recycler_view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        searchBar = root.findViewById(R.id.search_bar);

        n2ViewModel = new ViewModelProvider(this).get(N2ViewModel.class);
        n2ViewModel.getRecipes().observe(getViewLifecycleOwner(), recipes -> {
            adapter = new DataAdapter(recipes, this::onItemClick);
            recyclerView.setAdapter(adapter);
        });

        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                String query = searchBar.getText().toString().trim();
                if (!query.isEmpty()) {
                    navigateToSearchResults(query);
                }
                return true;
            }
            return false;
        });

        return root;
    }

    private void navigateToSearchResults(String query) {
        Bundle bundle = new Bundle();
        bundle.putString("query", query);
        try {
            Navigation.findNavController(requireView()).navigate(R.id.action_n2Fragment_to_searchResultFragment, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onItemClick(CookRecipeResponse.RecipeRow item) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("tit", item.getRCP_NM());
        intent.putExtra("cate", item.getRCP_PAT2());
        intent.putExtra("image", item.getATT_FILE_NO_MAIN());
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
