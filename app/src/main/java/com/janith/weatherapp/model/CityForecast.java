package com.janith.weatherapp.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by janith on 4/4/16.
 */
public class CityForecast implements Serializable {

    private int cityId;
    private String cityName;
    private Date sunRise;
    private Date sunSet;
    private String weatherBrief;
    private String weatherDescription;
    private int temperature;
    private int pressure;
    private int humidity;
    private float windSpeed;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getSunRise() {
        return sunRise;
    }

    public void setSunRise(Date sunRise) {
        this.sunRise = sunRise;
    }

    public Date getSunSet() {
        return sunSet;
    }

    public void setSunSet(Date sunSet) {
        this.sunSet = sunSet;
    }

    public String getWeatherBrief() {
        return weatherBrief;
    }

    public void setWeatherBrief(String weatherBrief) {
        this.weatherBrief = weatherBrief;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public String toString() {
        return "CityForecast{" +
                "cityName='" + cityName + '\'' +
                '}';
    }

    public String getForcastString(){
        String forcast;
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a", Locale.US);
        forcast = "City    : " + cityName + "\n\n" +
                "Sunrise : " + dateFormat.format(sunRise) + "\n" +
                "Sunset  : " + dateFormat.format(sunSet) + "\n\n" +
                "Weather Brief : " + weatherDescription + "\n\n" +
                "Temperature  : " + temperature + " Celsius\n" +
                "Pressure : " + pressure +" hPa\n" +
                "Humidity : " + humidity + "%\n" +
                "Wind Speed : " + windSpeed + " mps";

        return forcast;
    }

}
