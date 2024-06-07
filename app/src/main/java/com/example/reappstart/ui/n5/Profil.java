package com.example.reappstart.ui.n5;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.reappstart.databinding.ProfilBinding;

public class Profil extends Activity {
    private ProfilBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

}
