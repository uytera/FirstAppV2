package com.example.firstappv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements AbyssiDialog.NoticeDialogListener {
    TextView topText;
    Button middleButton;
    Button deepButton;
    Button caputButton;
    ConstraintLayout view;
    com.google.android.material.textfield.TextInputLayout editTextMaterial;
    EditText editText;
    com.google.android.material.textfield.TextInputLayout caputEditTextMaterial;
    EditText caputEditText;
    SharedPreferences sharedPreferences;
    SensorManager sm;
    Sensor sensorLight;


    public static String textF = "nomen";
    public static String textCaput = "caput";


    SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            int currValue = (int) sensorEvent.values[0];
            deepButton.getBackground().setAlpha(255 - (Math.min((currValue * 10), 255)));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getPreferences(MODE_PRIVATE);

        view = findViewById(R.id.currentAct);
        topText = findViewById(R.id.topText);
        editTextMaterial = findViewById(R.id.nomen);
        editText = editTextMaterial.getEditText();
        deepButton = findViewById(R.id.deepButton);
        caputButton = findViewById(R.id.caputButton);
        caputEditTextMaterial = findViewById(R.id.nomineCaput);
        caputEditText = caputEditTextMaterial.getEditText();

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorLight = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sm.registerListener(sensorEventListener,
                sensorLight,
                SensorManager.SENSOR_DELAY_NORMAL);

        if (sharedPreferences.contains(textCaput)) {
            setTitle(sharedPreferences.getString(textCaput, ""));
        } else {
            setTitle("sine nomine");
        }

        deepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbyssiDialog diag = new AbyssiDialog();
                diag.show(getSupportFragmentManager(), "NoticeMeUwU");
            }
        });

        caputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(textCaput, String.valueOf(caputEditText.getText()));
                editor.apply();
                setTitle(caputEditText.getText());
            }
        });
    }

    public void sensorLight(View view) {

    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Intent intent = new Intent(this, TenebrisActivity.class);
        intent.putExtra(textF, editText.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sample, menu);
        return true;
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
