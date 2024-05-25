package com.example.reappstart.ui.n2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.reappstart.databinding.FragmentN2Binding;

public class N2Fragment extends Fragment {

    private FragmentN2Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        N2ViewModel n2ViewModel =
                new ViewModelProvider(this).get(N2ViewModel.class);

        binding = FragmentN2Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textN2;
        n2ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}