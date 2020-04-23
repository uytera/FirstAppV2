package com.example.firstappv2;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TenebrisActivity extends AppCompatActivity {
    TextView nomenQuaesitor;
    Button middleButton;
    SensorManager sm;
    Sensor sensorLight;

    SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            int currValue = (int) sensorEvent.values[0];
            middleButton.getBackground().setAlpha(Math.min((currValue * 10), 255));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tenebris_layout);
        hideSystemUI();
        Intent intent = getIntent();
        String text = intent.getStringExtra("nomen");

        nomenQuaesitor = findViewById(R.id.nomenQuaesitor);
        nomenQuaesitor.setText(text);
        middleButton = findViewById(R.id.middleButton2);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorLight = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sm.registerListener(sensorEventListener,
                sensorLight,
                SensorManager.SENSOR_DELAY_NORMAL);

        middleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Change();
            }
        });
    }

    public void Change() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
