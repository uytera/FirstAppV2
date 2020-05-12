package com.example.firstappv2.WeatherDataList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherContent {

    public static final List<WeatherDateItem> ITEMS = new ArrayList<WeatherDateItem>();

    public static final Map<String, WeatherDateItem> ITEM_MAP = new HashMap<String, WeatherDateItem>();

    private static final int COUNT = 25;

    public static void addItem(WeatherDateItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.date, item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class WeatherDateItem {
        public final String date;
        public final String city;
        public final float temp;
        public final float wind;

        public WeatherDateItem(String date, String city, float temp, float wind) {
            this.date = date;
            this.city = city;
            this.temp = temp;
            this.wind = wind;
        }

        @Override
        public String toString() {
            return city;
        }
    }
}
