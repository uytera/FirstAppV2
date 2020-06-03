package com.example.firstappv2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.firstappv2.R;
import com.example.firstappv2.WeatherActivity;
import com.example.firstappv2.WeatherTempListActivity;
import com.example.firstappv2.dialogs.CityDialog;
import com.example.firstappv2.presentor.MainPresentor;

public class MainActivity extends AppCompatActivity implements CityDialog.NoticeDialogListener {
    TextView topText;
    Button deepButton;
    //Button caputButton;
    ImageButton listButton;
    ConstraintLayout view;
    com.google.android.material.textfield.TextInputLayout editTextMaterial;
    EditText editText;
    com.google.android.material.textfield.TextInputLayout caputEditTextMaterial;
    EditText caputEditText;
    SharedPreferences sharedPreferences;

    private static MainPresentor mainPresentor;
    public static String textF = "nomen";
    public static String textCaput = "caput";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getPreferences(MODE_PRIVATE);

        mainPresentor = new MainPresentor(this);
        view = findViewById(R.id.currentAct);
        topText = findViewById(R.id.topText);
        editTextMaterial = findViewById(R.id.nomen);
        editText = editTextMaterial.getEditText();
        listButton = findViewById(R.id.listButton2);
        deepButton = findViewById(R.id.deepButton);
        //caputButton = findViewById(R.id.caputButton);
        caputEditTextMaterial = findViewById(R.id.nomineCaput);
        caputEditText = caputEditTextMaterial.getEditText();

        if (sharedPreferences.contains(textCaput)) {
            setTitle(sharedPreferences.getString(textCaput, ""));
        } else {
            setTitle("sine nomine");
        }

        deepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresentor.deepButtonClick(getSupportFragmentManager());
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWeatherListClick();
            }
        });

//        caputButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString(textCaput, String.valueOf(caputEditText.getText()));
//                editor.apply();
//                setTitle(caputEditText.getText());
//            }
//        });
    }

    public void onWeatherListClick() {
        Intent intent = new Intent(this, WeatherTempListActivity.class);
        mainPresentor.startIntendActivity(intent);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Intent intent = new Intent(this, WeatherActivity.class);
        mainPresentor.putIntendValue(textF, editText.getText().toString(), intent);
        mainPresentor.startIntendActivity(intent);
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
        View mDecorView = this.getWindow().getDecorView();
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    private void showSystemUI() {
        View mDecorView = this.getWindow().getDecorView();
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
