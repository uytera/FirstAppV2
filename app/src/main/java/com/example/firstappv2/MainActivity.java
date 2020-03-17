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

        view = (ConstraintLayout) findViewById(R.id.currentAct);
        topText = (TextView) findViewById(R.id.topText);
        middleButton = (Button) findViewById(R.id.middleButton);
        deepButton = (Button) findViewById(R.id.deepButton);

        middleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topText.setText("aurea mediocritas");
                topText.setTextColor(Color.parseColor("#EFB135"));
                middleButton.setVisibility(View.INVISIBLE);
                deepButton.setVisibility(View.VISIBLE);
                view.setBackgroundColor(R.drawable.gradient_middle);
            }
        });

        deepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topText.setText("tenebris profundis");
                topText.setTextColor(Color.parseColor("#000000"));
                middleButton.setVisibility(View.VISIBLE);
                deepButton.setVisibility(View.INVISIBLE);
                view.setBackgroundColor(R.drawable.gradient_deep);
            }
        });
    }
}
