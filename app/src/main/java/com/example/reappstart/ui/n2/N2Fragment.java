package com.example.reappstart.ui.n2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reappstart.DetailActivity;
import com.example.reappstart.R;
import com.example.reappstart.database.CookRecipeResponse;
import com.example.reappstart.databinding.FragmentN2Binding;
import com.example.reappstart.ui.n1.DataAdapter;


public class N2Fragment extends Fragment {
    private N2ViewModel n2ViewModel;
    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private EditText searchBar;

    private FragmentN2Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_n2, container, false);

        recyclerView = root.findViewById(R.id.recycler_view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        searchBar = root.findViewById(R.id.search_bar);

        n2ViewModel = new ViewModelProvider(this).get(N2ViewModel.class);
        n2ViewModel.getRecipes().observe(getViewLifecycleOwner(), recipes -> {
            adapter = new DataAdapter(recipes, this::onItemClick);
            recyclerView.setAdapter(adapter);
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String query = s.toString().trim();
                if (!query.isEmpty()) {
                    searchRecipes(query);
                }
            }
        });
//        final TextView textView = binding.textN2;
//        n2ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private void searchRecipes(String query) {
        Bundle bundle = new Bundle();
        try {
            Navigation.findNavController(requireView()).navigate(R.id.action_n2Fragment_to_searchResultFragment, bundle);
        } catch (Exception ignore) {

        }
    }

    private void onItemClick(CookRecipeResponse.RecipeRow item) {
        // 아이템 클릭 시 동작을 정의합니다.
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