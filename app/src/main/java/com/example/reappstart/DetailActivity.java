package com.example.reappstart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;


public class DetailActivity extends AppCompatActivity {
    TextView txt, ca;
    ImageView iv;
    ImageButton ed;
    String text, img, ctgr;
    Intent i;
    View.OnClickListener cl;
    FrameLayout bottomBox;
    Float startY;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        bottomBox = (FrameLayout) findViewById(R.id.bottomBox);
        iv = (ImageView) findViewById(R.id.image);
        txt = (TextView) findViewById(R.id.title);
        ca = (TextView) findViewById(R.id.category);
        ed = (ImageButton) findViewById(R.id.end);
        i = getIntent();
        text = i.getStringExtra("tit");
        img = i.getStringExtra("image");
        ctgr = i.getStringExtra("category");
        txt.setText(text);
        ca.setText(ctgr);
        Log.d("DetailActivity", "Image URL: " + img); // 이미지 URL 로그

        Glide.with(this)
                .load(img)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        iv.setImageDrawable(resource);
                        iv.invalidate();
                        iv.requestLayout();
                        Log.d("DetailActivity", "Image loaded successfully"); // 이미지 로드 성공 로그
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        iv.setImageDrawable(placeholder);
                        Log.d("DetailActivity", "Image load cleared"); // 이미지 로드 해제 로그
                    }
                });

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if ( id == R.id.end ){
                    finish();
                }
            }
        };

        ed.setOnClickListener(cl);
        bottomBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        float deltaY = startY - event.getRawY();
                        int newHeight = (int) (bottomBox.getHeight() + deltaY);
                        if (newHeight >= 400) { // 음수 높이 방지
                            bottomBox.getLayoutParams().height = newHeight;
                            bottomBox.requestLayout();
                        }
                        startY = event.getRawY();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
