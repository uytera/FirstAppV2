package com.example.firstappv2.fragments.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstappv2.R;
import com.example.firstappv2.weatherDataList.MyweatherDataRecyclerViewAdapter;
import com.example.firstappv2.weatherDataList.WeatherContent;

public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.temp_list, container, false);

        recyclerView = root.findViewById(R.id.weather_fragment);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyweatherDataRecyclerViewAdapter(WeatherContent.ITEMS);
        recyclerView.setAdapter(mAdapter);

        return root;
    }
}
