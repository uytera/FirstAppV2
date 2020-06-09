package com.example.firstappv2.fragments.main;

import androidx.fragment.app.FragmentManager;
import com.example.firstappv2.NavMainActivity;
import com.example.firstappv2.dialogs.CityDialog;

public class MainFragmentPresentor {
    private MainFragment mView;

    public MainFragmentPresentor(MainFragment view){
        mView = view;
    }

    public void deepButtonClick(FragmentManager manager, String city){
        NavMainActivity.city = city;
        CityDialog diag = new CityDialog();
        diag.show(manager, "NoticeMeUwU");
    }
}
