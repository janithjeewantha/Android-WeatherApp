package com.janith.weatherapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.janith.weatherapp.controller.FetchData;
import com.janith.weatherapp.controller.Utilities;
import com.janith.weatherapp.model.CityForecast;
import com.janith.weatherapp.model.Forecast;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements
        CityListFragment.OnCitySelectedListener {

    private Forecast forecast;
    public String jsonStr;
    private CityListFragment cityListFragment = null;
    private DetailFragment detailFragment = null;
    private URL weatherApiUrl;
    private Intent intent;
    public final static String FORECAST = "com.janith.weatherapp.FORECAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Utilities.isTab(this)){
            tabletConfig();
        } else {
            handsetConfig();
        }

        getPermissions();
        FetchData fetcher = new FetchData();
        fetcher.setActivity(this);
        try {
            weatherApiUrl = new URL("http://api.openweathermap.org/data/2.5/group?id=1248991,1246294," +
                    "1241622,1235846,1231410,1241964,1244178,1251574,1237980,1226260,1242833&" +
                    "units=metric&appid=3b2fb05205935454966162c87c9e77e3");
            fetcher.execute(weatherApiUrl);
        } catch (MalformedURLException e) {
            Log.d("URL Error", "Weather API URL is malformed");
        }
    }

    @Override
    public void onCitySelected(int position) {

        TextView detailTextView = null;
        if (Utilities.isTab) {
            detailTextView = (TextView) findViewById(R.id.detail_field);
        }

        String[] citiesList = getResources().getStringArray(R.array.cities_list);
        String city = citiesList[position];

        if(jsonStr == null || jsonStr.isEmpty()){
            showToast("Weather Data Acquiring Failed!");
        }

        if (detailTextView != null){
            detailTextView.setText(null);
            if(forecast!=null){
                CityForecast cityForecast = forecast.getCityForcast(city);
                detailTextView.setText(cityForecast.getForcastString());
            }
        }

        if(!Utilities.isTab){
            if(forecast!=null & intent!=null) {
                CityForecast cityForecast = forecast.getCityForcast(city);
//                showDetailFragment(cityForecast.getForecastString());
                intent.putExtra(FORECAST, cityForecast.getForcastString());
                startActivity(intent);

            }
        }
    }

    private void tabletConfig(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        FrameLayout detailFrame = (FrameLayout) findViewById(R.id.list_container);
        detailFrame.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));

        if (detailFragment == null) {
            detailFragment = new DetailFragment();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, detailFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        transaction.commit();

        if (cityListFragment == null) {
            cityListFragment = new CityListFragment();
        }
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        transaction2.replace(R.id.list_container, cityListFragment);
        transaction2.setTransition(FragmentTransaction.TRANSIT_NONE);
        transaction2.commit();
    }

    private void handsetConfig() {

        if (cityListFragment == null) {
            cityListFragment = new CityListFragment();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.list_container, cityListFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        transaction.commit();

        intent = new Intent(this, DetailActivity.class);

    }

    private void showDetailFragment(String msg) {

        FrameLayout detailFrame = (FrameLayout) findViewById(R.id.list_container);
        detailFrame.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 0));

        if (detailFragment == null) {
            detailFragment = new DetailFragment();
        }
        detailFragment.msg = msg;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.list_container, detailFragment);
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        transaction.commit();
    }

    private void getPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.INTERNET)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET},10);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET},10);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (forecast != null) {
            outState.putSerializable("forecast", forecast);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        forecast = (Forecast) savedInstanceState.getSerializable("forecast");
    }

    public void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void dataAcquired(String jsonString) {
        if (jsonString != null) {
            jsonStr = jsonString;
            showToast("Weather Data Acquired");
            forecast = Utilities.parseJson(jsonString);
        } else {
            showToast("JSON Acquire Failed");
        }
    }
}
