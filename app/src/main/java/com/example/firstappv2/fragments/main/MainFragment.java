package com.example.firstappv2.fragments.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.firstappv2.NavMainActivity;
import com.example.firstappv2.R;
import com.example.firstappv2.fragments.weather.WeatherFragment;

public class MainFragment extends Fragment{
    private Button deepButton;
    private com.google.android.material.textfield.TextInputLayout editTextMaterial;
    private EditText editText;

    private static MainFragmentPresentor mainPresentor;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_main, container, false);

        NavMainActivity.weatherFragment = new WeatherFragment();

        mainPresentor = new MainFragmentPresentor(this);
        editTextMaterial = root.findViewById(R.id.nomen);
        editText = editTextMaterial.getEditText();
        deepButton = root.findViewById(R.id.deepButton);

        deepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresentor.deepButtonClick(getFragmentManager(), editText.getText().toString());
            }
        });

        return root;
    }
}
