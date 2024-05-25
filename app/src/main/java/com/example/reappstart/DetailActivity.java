package com.example.reappstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    TextView txt;
    Button ed;
    String text;
    Intent i;
    View.OnClickListener cl;
    FrameLayout bottomBox;
    Float startY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        bottomBox = (FrameLayout) findViewById(R.id.bottomBox);
        txt = (TextView) findViewById(R.id.test);
        ed = (Button) findViewById(R.id.end);
        i = getIntent();
        text = i.getStringExtra("tit");
        txt.setText(text+"레시피");

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
}
