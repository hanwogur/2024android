package com.example.reappstart.ui.n5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.reappstart.MainActivity;
import com.example.reappstart.R;
import com.example.reappstart.databinding.ProfilBinding;

public class Profil extends Activity {
    private ProfilBinding binding;
    DBHelper db = new DBHelper(this);
    boolean refresh = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sp = getSharedPreferences("member", Activity.MODE_PRIVATE);
        String id = sp.getString("id", null);
        String name = sp.getString("name", null);

        Intent intent = getIntent();
        refresh = intent.getBooleanExtra("refresh", false);

        if (refresh){
            binding.btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Profil.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                    finish();
                }
            });
        } else {
            binding.btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        binding.image.setBackgroundResource(R.drawable.baseline_account_circle_24);
        binding.name.setText(name+"");
        binding.id.setText("#"+id);

        binding.nameModi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Profil.this);
                builder.setTitle("닉네임 변경");

                final EditText inp = new EditText(Profil.this);
                builder.setView(inp);
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sp = getSharedPreferences("member", Activity.MODE_PRIVATE);
                        String id = sp.getString("id", null);

                        String newName = inp.getText().toString();
                        db.changeName(id, newName);

                        SharedPreferences.Editor spe = sp.edit();
                        spe.putString("name", newName);
                        spe.commit();

                        refresh = true;

                        Toast.makeText(Profil.this, "변경되었습니다", Toast.LENGTH_SHORT).show();

                        //Intent intent = new Intent(Profil.this, MainActivity.class);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        //startActivity(intent);

                        Intent intent = getIntent();
                        intent.putExtra("refresh", refresh);
                        finish();
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }

}
