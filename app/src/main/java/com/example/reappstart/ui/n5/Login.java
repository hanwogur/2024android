package com.example.reappstart.ui.n5;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.reappstart.databinding.LoginBinding;

import java.util.ArrayList;

public class Login extends Activity {

    private LoginBinding binding;

    DBHelper db = new DBHelper(this);
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

                ArrayList<String> r = db.login(id, pw);
                Log.v("c2", "go");

                if (r != null){
                    Toast.makeText(Login.this, "로그인 성공:)", Toast.LENGTH_SHORT).show();

                    SharedPreferences sp = getSharedPreferences("member", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor spe = sp.edit();
                    spe.putString("id", r.get(0));
                    spe.putString("name", r.get(1));
                    spe.putString("phone", r.get(2));
                    spe.commit();

                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);

                    finish();
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
