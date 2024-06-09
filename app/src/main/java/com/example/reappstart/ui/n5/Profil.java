package com.example.reappstart.ui.n5;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.reappstart.MainActivity;
import com.example.reappstart.R;
import com.example.reappstart.databinding.ProfilBinding;

public class Profil extends Activity {
    private ProfilBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sp = getSharedPreferences("member", Activity.MODE_PRIVATE);
        String id = sp.getString("id", null);
        String name = sp.getString("name", null);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.image.setBackgroundResource(R.drawable.baseline_account_circle_24);
        binding.name.setText(name+"");
        binding.id.setText("#"+id);
    }

}
