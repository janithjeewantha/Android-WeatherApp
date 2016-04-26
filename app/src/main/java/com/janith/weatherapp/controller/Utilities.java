package com.janith.weatherapp.controller;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;

import com.janith.weatherapp.model.CityForecast;
import com.janith.weatherapp.model.Forecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Locale;

/**
 * Created by janith on 4/3/16.
 */
public class Utilities {

    public static boolean isTab;

    public static boolean isTab(Activity activity) {

        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        float yInches= metrics.heightPixels/metrics.ydpi;
        float xInches= metrics.widthPixels/metrics.xdpi;

        double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);

        isTab = diagonalInches >= 4.4;

        return isTab;
    }

    public static Forecast parseJson(String jsonString) {
        Forecast forecast = new Forecast();
        try {

            JSONObject mainJson = new JSONObject(jsonString);
            JSONArray forcastList = mainJson.optJSONArray("list");

            for(int i=0; i < forcastList.length(); i++) {
                JSONObject forcastJson = forcastList.getJSONObject(i);
                int id = forcastJson.getInt("id");
                String name = forcastJson.getString("name");
                JSONObject sys = forcastJson.getJSONObject("sys");
                long sunrise = sys.getLong("sunrise");
                long sunset = sys.getLong("sunset");
                JSONArray weatherArray = forcastJson.getJSONArray("weather");
                JSONObject weather = weatherArray.getJSONObject(0);
                String brief = weather.getString("main");
                String desc = weather.getString("description");
                JSONObject mainData = forcastJson.getJSONObject("main");
                int temperature =  mainData.getInt("temp");
                int pressure =  mainData.getInt("pressure");
                int humidity =  mainData.getInt("humidity");
                JSONObject wind = forcastJson.getJSONObject("wind");
                float windSpeed = (float) wind.getDouble("speed");

                CityForecast cityForecast = new CityForecast();
                cityForecast.setCityId(id);
                cityForecast.setCityName(name);
                Date date = new Date(sunrise);
                
                cityForecast.setSunRise(new Date(sunrise));
                cityForecast.setSunSet(new Date(sunset));
                cityForecast.setWeatherBrief(brief);
                cityForecast.setWeatherDescription(desc);
                cityForecast.setTemperature(temperature);
                cityForecast.setPressure(pressure);
                cityForecast.setHumidity(humidity);
                cityForecast.setWindSpeed(windSpeed);
                forecast.addCityForcast(name, cityForecast);
            }
        } catch (JSONException e){
//            e.printStackTrace();
        }
        return forecast;
    }
}
