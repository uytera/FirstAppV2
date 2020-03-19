package com.example.firstappv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView topText;
    Button middleButton;
    Button deepButton;
    ConstraintLayout view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideSystemUI();

        view = (ConstraintLayout) findViewById(R.id.currentAct);
        topText = (TextView) findViewById(R.id.topText);
        middleButton = (Button) findViewById(R.id.middleButton);
        deepButton = (Button) findViewById(R.id.deepButton);

        middleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSystemUI();
                topText.setText("aurea mediocritas");
                topText.setTextColor(Color.parseColor("#EFB135"));
                middleButton.setVisibility(View.INVISIBLE);
                deepButton.setVisibility(View.VISIBLE);
                view.setBackgroundResource(R.drawable.gradient_middle);
            }
        });

        deepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSystemUI();
                topText.setText("tenebris profundis");
                topText.setTextColor(Color.parseColor("#000000"));
                middleButton.setVisibility(View.VISIBLE);
                deepButton.setVisibility(View.INVISIBLE);
                view.setBackgroundResource(R.drawable.gradient_deep);
            }
        });
    }

    private void hideSystemUI() {
        View mDecorView = getWindow().getDecorView();
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    private void showSystemUI() {
        View mDecorView = getWindow().getDecorView();
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
