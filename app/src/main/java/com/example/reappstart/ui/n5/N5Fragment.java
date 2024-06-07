package com.example.reappstart.ui.n5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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

        SharedPreferences sp = getActivity().getSharedPreferences("member", getActivity().MODE_PRIVATE);
        String id = sp.getString("id", null);
        String name = sp.getString("name", null);

        if (id != null){
            binding.name.setText(name+"");
            binding.btn.setText("더보기");
            binding.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), Profil.class);
                    startActivity(i);
                }
            });

            View.OnClickListener cl = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                }
            };

        } else {
            binding.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), Login.class);
                    startActivity(i);
                }
            });
        }

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }






}