package com.example.firstappv2.fragments.weather;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.firstappv2.NavMainActivity;
import com.example.firstappv2.R;
import com.example.firstappv2.request.JsonRequest;
import com.example.firstappv2.weatherDataList.WeatherContent;

import org.json.JSONObject;

import java.util.Date;
import java.util.Objects;

public class WeatherFragment extends Fragment {
    private TextView city;
    private TextView temp;
    private TextView windSp;

    private static final int NOTIFY_ID = 101;

    private void notifyMethod(String city, String NotText){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(Objects.requireNonNull(getContext()));
        String CHANNEL_ID = "Weather channel";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_wb_sunny_24px)
                        .setContentTitle("Погода в " + city)
                        .setContentText(NotText)
                        .setChannelId(CHANNEL_ID)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "internet Permission Granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tenebris_layout, container, false);

        city = root.findViewById(R.id.city);
        temp = root.findViewById(R.id.temp);
        windSp = root.findViewById(R.id.windSp);

        JSONObject json = null;
        String cityName = NavMainActivity.city;
        String apiKey = "18d82c5a0c9f97611a9864ef7b3c2d34";
        city.setText(cityName);

        int permissionStatus = ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.INTERNET);

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            WeatherFragment.AsyncRequest task = new WeatherFragment.AsyncRequest();
            task.execute(cityName, apiKey);
        } else {
            int REQUEST_CODE_PERMISSION_INTERNET = 0;
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[] {Manifest.permission.INTERNET}, REQUEST_CODE_PERMISSION_INTERNET);
            WeatherFragment.AsyncRequest task = new WeatherFragment.AsyncRequest();
            task.execute(cityName, apiKey);
        }

        return root;
    }

    class AsyncRequest extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... arg) {
            return JsonRequest.readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q=" + arg[0] +"&appid=" + arg[1] + "&units=metric");
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                float tempF  = (float) json.getJSONObject("main").getDouble("temp");
                float windF = (float) json.getJSONObject("wind").getInt("speed");
                WeatherContent.addItem(new WeatherContent.WeatherDateItem(new Date().toString(), city.getText().toString(), tempF, windF));
                temp.setText("Температура: " + tempF);
                windSp.setText("Скорость ветра: " +  windF);
                String notifyString = "Температура: " + tempF + "Скорость ветра: " +  windF;
                notifyMethod(city.getText().toString() ,notifyString);

            }catch (Exception e) {
                temp.setText("Неверный город");
            }
        }
    }
}
