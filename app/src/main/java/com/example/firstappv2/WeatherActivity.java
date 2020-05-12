package com.example.firstappv2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import com.example.firstappv2.request.JsonRequest;
import com.example.firstappv2.weatherDataList.WeatherContent;
import org.json.JSONObject;
import java.util.Date;

public class WeatherActivity extends AppCompatActivity {

    static TextView city;
    static TextView temp;
    static TextView windSp;
    int REQUEST_CODE_PERMISSION_INTERNET = 0;

    ImageButton returnButton;
    ImageButton listButton;

    private static final int NOTIFY_ID = 101;
    private static String CHANNEL_ID = "Weather channel";

    private void notifyMethod(String city, String NotText){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(WeatherActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_wb_sunny_24px)
                        .setContentTitle("Погода в " + city)
                        .setContentText(NotText)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(WeatherActivity.this);
        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "internet Permission Granted", Toast.LENGTH_SHORT).show();
                }
                else {}
                return;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tenebris_layout);
        Intent intent = getIntent();

        city = findViewById(R.id.city);
        temp = findViewById(R.id.temp);
        windSp = findViewById(R.id.windSp);


        returnButton = findViewById(R.id.returnButton);
        listButton = findViewById(R.id.listButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Return();
            }
        });
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWeatherListClick();
            }
        });

        JSONObject json = null;
        String cityName = intent.getStringExtra("nomen");
        String apiKey = "18d82c5a0c9f97611a9864ef7b3c2d34";
        city.setText(cityName);

        int permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            AsyncRequest task = new AsyncRequest();
            task.execute(cityName, apiKey);
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.INTERNET}, REQUEST_CODE_PERMISSION_INTERNET);
            AsyncRequest task = new AsyncRequest();
            task.execute(cityName, apiKey);
        }
    }

    public void Return() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onWeatherListClick() {
        Intent intentWeather = new Intent(this, WeatherTempListActivity.class);
        startActivity(intentWeather);
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
