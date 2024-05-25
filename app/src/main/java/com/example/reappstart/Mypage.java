package com.example.reappstart;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class Mypage extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_n5);
        ImageView imageView = findViewById(R.id.image);
        imageView.setImageResource(R.drawable.baseline_account_circle_24);
    }
}
