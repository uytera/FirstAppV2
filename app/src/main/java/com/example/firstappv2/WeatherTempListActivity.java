package com.example.firstappv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.firstappv2.WeatherDataList.WeatherContent;

public class WeatherTempListActivity extends FragmentActivity {
    ImageButton returnButton2;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_list);
        recyclerView = (RecyclerView) findViewById(R.id.weather_fragment);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyweatherDataRecyclerViewAdapter(WeatherContent.ITEMS);
        recyclerView.setAdapter(mAdapter);

        returnButton2 = findViewById(R.id.returnButton2);

        returnButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Return();
            }
        });
    }

    public void Return() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}