package com.example.reappstart.ui.n5;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.reappstart.SQLiteHelper;

import com.example.reappstart.databinding.LoginBinding;

public class Login extends Activity {

    private LoginBinding binding;
    SQLiteHelper db = new SQLiteHelper(this);
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = binding.id.getText().toString();
                String pw = binding.pw.getText().toString();
                String name = db.login(id, pw);

                if (name != null){
                    Toast.makeText(Login.this, "로그인 성공:)", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("a", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    onBackPressed();
                } else {
                    Toast.makeText(Login.this, "다시 확인해주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Sign.class);
                startActivity(i);
            }
        });

    }
}
