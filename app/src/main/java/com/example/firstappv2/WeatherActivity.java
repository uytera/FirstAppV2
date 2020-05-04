package com.example.firstappv2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class WeatherActivity extends AppCompatActivity {
    static TextView city;
    static TextView temp;
    static TextView windSp;
    int REQUEST_CODE_PERMISSION_INTERNET = 0;
    ImageButton middleButton;
    SensorManager sm;
    Sensor sensorLight;

    SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            int currValue = (int) sensorEvent.values[0];
            middleButton.getBackground().setAlpha(Math.min((currValue * 10), 255));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

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
        hideSystemUI();
        Intent intent = getIntent();

        city = findViewById(R.id.city);
        temp = findViewById(R.id.temp);
        windSp = findViewById(R.id.windSp);



        JSONObject json = null;
        String cityName = intent.getStringExtra("nomen");
        String apiKey = "18d82c5a0c9f97611a9864ef7b3c2d34";
        System.out.println("http://api.openweathermap.org/data/2.5/weather?q=" + cityName +"&appid=" + apiKey);

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




        middleButton = findViewById(R.id.middleButton2);


        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorLight = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sm.registerListener(sensorEventListener,
                sensorLight,
                SensorManager.SENSOR_DELAY_NORMAL);

        middleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Change();
            }
        });
    }

    protected void Request(String  cityName, String apiKey){

    }

    protected static String readAll(Reader bufferedReader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int curretChar;
        while ((curretChar = bufferedReader.read()) != -1) {
            stringBuilder.append((char) curretChar);
        }
        return stringBuilder.toString();
    }

    protected static JSONObject readJsonFromUrl(String url) {
        JSONObject json = null;

        try(InputStream inputStream = new URL(url).openStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String jsonText = readAll(bufferedReader);
            json = new JSONObject(jsonText);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public void Change() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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

    static class AsyncRequest extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... arg) {
            return readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q=" + arg[0] +"&appid=" + arg[1] + "&units=metric");
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                float tempF  = (float) json.getJSONObject("main").getDouble("temp");
                float windF = (float) json.getJSONObject("wind").getInt("speed");
                temp.setText("Температура: " + tempF);
                windSp.setText("Скорость ветра: " +  windF);

            }catch (Exception e) {
                temp.setText("Неверный город");
            }
        }
    }
}
