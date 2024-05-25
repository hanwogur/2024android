package com.example.reappstart.ui.n4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.reappstart.databinding.FragmentN4Binding;

public class N4Fragment extends Fragment {

    private FragmentN4Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        N4ViewModel n4ViewModel =
                new ViewModelProvider(this).get(N4ViewModel.class);

        binding = FragmentN4Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textN4;
        n4ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}