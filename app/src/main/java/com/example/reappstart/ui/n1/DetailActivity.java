package com.example.reappstart.ui.n1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.reappstart.R;

import java.util.List;
import java.util.Map;


public class DetailActivity extends AppCompatActivity {
    TextView txt, ca;
    ImageView iv;
    ImageButton ed;
    String text, img, ctgr, rcpSeq;
    Intent i;
    View.OnClickListener cl;
    RecyclerView recyclerView;
    RecipeAdapter adapter;
    List<Map<String, String>> recipeSteps;
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
        recyclerView = findViewById(R.id.recipe_recy);

        i = getIntent();
        text = i.getStringExtra("tit");
        img = i.getStringExtra("image");
        ctgr = i.getStringExtra("category");
        rcpSeq = i.getStringExtra("RCP_SEQ");

        Log.d("DetailActivity", "RCP_SEQ from intent: " + rcpSeq); // 로그 추가

        if (rcpSeq == null || rcpSeq.isEmpty()) {
            Log.e("DetailActivity", "RCP_SEQ is null or empty");
            finish();
            return;
        }
        txt.setText(text);
        ca.setText(ctgr);
        Log.d("DetailActivity", "Image URL: " + img); // 이미지 URL 로그
        Log.d("DetailActivity", "RCP_SEQ: " + rcpSeq);

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

        try {
            RecipeHelper recipeHelper = new RecipeHelper(this);
            recipeSteps = recipeHelper.getRecipeSteps(rcpSeq);

            Log.d("DetailActivity", "Recipe steps loaded: " + recipeSteps.size());

            adapter = new RecipeAdapter(this, recipeSteps);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            Log.e("DetailActivity", "Error loading recipe steps", e);
        }
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
