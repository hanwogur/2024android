package com.example.reappstart.ui.n5;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.reappstart.R;
import com.example.reappstart.databinding.FragmentN5Binding;

public class N5Fragment extends Fragment {

    private FragmentN5Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        N5ViewModel n5ViewModel =
                new ViewModelProvider(this).get(N5ViewModel.class);

        binding = FragmentN5Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textN5;
        n5ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Login.class);
                startActivity(i);
            }
        });

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }






}