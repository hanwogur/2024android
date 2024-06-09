package com.example.reappstart.ui.n5;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.reappstart.MainActivity;
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

        SharedPreferences sp = getActivity().getSharedPreferences("member", Activity.MODE_PRIVATE);
        String id = sp.getString("id", null);
        String name = sp.getString("name", null);

        if (id != null){ // 씬 1
            binding.image.setBackgroundResource(R.drawable.baseline_account_circle_24);
            binding.name.setText(name+"");
            binding.btn.setText("더보기");
            binding.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), Profil.class);
                    startActivity(i);
                }
            });

            binding.button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), Setting.class);
                    startActivity(i);
                }
            });

        } else { // 씬 2
            binding.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), Login.class);
                    startActivity(i);
                }
            });

            View.OnClickListener cl = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "로그인이 필요합니다", Toast.LENGTH_SHORT).show();
                }
            };
            binding.button.setOnClickListener(cl);
            binding.button2.setOnClickListener(cl);
            binding.button3.setOnClickListener(cl);
            binding.button4.setOnClickListener(cl);
            binding.button5.setOnClickListener(cl);
            binding.button6.setOnClickListener(cl);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }









}