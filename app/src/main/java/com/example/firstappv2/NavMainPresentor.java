package com.example.firstappv2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NavMainPresentor {

    private NavMainActivity mView;

    public NavMainPresentor(NavMainActivity view){
        mView = view;
    }

    public void changeFragment(FragmentManager fragmentManager,Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();
    }
}
