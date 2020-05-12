package com.example.firstappv2.widget;

import android.Manifest;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.widget.RemoteViews;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.firstappv2.R;
import com.example.firstappv2.request.JsonRequest;
import com.example.firstappv2.weatherDataList.WeatherContent;
import org.json.JSONObject;
import java.util.Date;

public class Widget extends AppWidgetProvider {
    private String city = "Москва";
    private String apiKey;
    private AppWidgetManager appMan;
    private RemoteViews remoteViews;
    private ComponentName componentName;
    private int REQUEST_CODE_PERMISSION_INTERNET = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);


        appMan = appWidgetManager;
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        componentName = new ComponentName(context, Widget.class);
        apiKey = "18d82c5a0c9f97611a9864ef7b3c2d34";
        int permissionStatus = ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET);

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            AsyncWidgetRequest task = new AsyncWidgetRequest();
            task.execute(city, apiKey);
        } else {
            remoteViews.setTextViewText(R.id.cityField, "Сначала запросите погоду в приложении");
            remoteViews.setTextViewText(R.id.tempField, "");
            remoteViews.setTextViewText(R.id.windField, "");
        }
    }

    class AsyncWidgetRequest extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... arg) {
            return JsonRequest.readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q=" + arg[0] +"&appid=" + arg[1] + "&units=metric");
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                float tempF  = (float) json.getJSONObject("main").getDouble("temp");
                float windF = (float) json.getJSONObject("wind").getInt("speed");
                WeatherContent.addItem(new WeatherContent.WeatherDateItem(new Date().toString(), city, tempF, windF));
                remoteViews.setTextViewText(R.id.cityField, city);
                remoteViews.setTextViewText(R.id.tempField, tempF + " С°");
                remoteViews.setTextViewText(R.id.windField, windF + " м/с");
                appMan.updateAppWidget(componentName,remoteViews);
            }catch (Exception e) {

            }
        }
    }
}

