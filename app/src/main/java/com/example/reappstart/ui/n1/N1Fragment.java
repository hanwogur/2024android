package com.example.reappstart.ui.n1;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reappstart.DetailActivity;
import com.example.reappstart.R;
import com.example.reappstart.databinding.FragmentN1Binding;
import com.example.reappstart.database.CookRecipeResponse;

import java.util.ArrayList;

public class N1Fragment extends Fragment {
    private FragmentN1Binding binding;
    private RecyclerView recyclerView;
    private DataAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        N1ViewModel n1ViewModel = new ViewModelProvider(this).get(N1ViewModel.class);

        binding = FragmentN1Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
