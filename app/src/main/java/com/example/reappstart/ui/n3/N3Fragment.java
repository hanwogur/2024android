package com.example.reappstart.ui.n3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.reappstart.R;
import com.example.reappstart.databinding.FragmentN3Binding;

public class N3Fragment extends Fragment {

    private FragmentN3Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        N3ViewModel n3ViewModel =
                new ViewModelProvider(this).get(N3ViewModel.class);

        binding = FragmentN3Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textN3;
        n3ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Animation rotation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anime);
        binding.iv.startAnimation(rotation);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}