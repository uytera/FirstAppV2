package com.example.firstappv2.presentor;

import android.content.Intent;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.example.firstappv2.WeatherActivity;
import com.example.firstappv2.WeatherTempListActivity;
import com.example.firstappv2.dialogs.CityDialog;
import com.example.firstappv2.view.MainActivity;

public class MainPresentor {
    private MainActivity mView;

    public MainPresentor(MainActivity view){
        mView = view;
    }

    public void deepButtonClick(FragmentManager manager){
        CityDialog diag = new CityDialog();
        diag.show(manager, "NoticeMeUwU");
    }

    public void listClick(){
        Intent intentWeather = new Intent(mView, WeatherTempListActivity.class);
        mView.startActivity(intentWeather);
    }

    public void putIntendValue(String key, String value, Intent intent){
        intent.putExtra(key, value);
    }

    public void startIntendActivity(Intent intent){
        mView.startActivity(intent);
    }
}
