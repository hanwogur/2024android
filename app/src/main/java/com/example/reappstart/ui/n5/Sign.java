package com.example.reappstart.ui.n5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.reappstart.databinding.SignBinding;

public class Sign extends Activity {

    private SignBinding b;

    private DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = SignBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        b.sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = b.id.getText().toString();
                String pw = b.pw.getText().toString();
                String pww = b.pww.getText().toString();
                String name = b.name.getText().toString();
                String phone = b.phone.getText().toString();

                if (pw.equals(pww)){
                    db.memberInsert(id, pw, name, phone);
                    Toast.makeText(Sign.this, "회원가입 완료", Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    Toast.makeText(Sign.this, "비밀번호가 맞지 않습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
