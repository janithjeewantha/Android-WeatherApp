package com.janith.weatherapp.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by janith on 4/5/16.
 */
public class Forecast implements Serializable {

    private Map<String, CityForecast> cityForcastMap = new HashMap();

    public void addCityForcast(String city, CityForecast forcast){
        cityForcastMap.put(city, forcast);
    }

    public CityForecast getCityForcast(String city){
        return cityForcastMap.get(city);
    }
}
