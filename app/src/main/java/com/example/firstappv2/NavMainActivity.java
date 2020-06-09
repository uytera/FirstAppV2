package com.example.firstappv2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.firstappv2.dialogs.CityDialog;
import com.example.firstappv2.fragments.weather.WeatherFragment;
import com.google.android.material.navigation.NavigationView;

public class NavMainActivity extends AppCompatActivity implements CityDialog.NoticeDialogListener {

    private AppBarConfiguration mAppBarConfiguration;
    public static Fragment weatherFragment;
    public static String city;
    private static NavMainPresentor navMainPresentor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navMainPresentor = new NavMainPresentor(this);
        weatherFragment = new WeatherFragment();

        setContentView(R.layout.nav_activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_list)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        navMainPresentor.changeFragment(fragmentManager, weatherFragment);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
