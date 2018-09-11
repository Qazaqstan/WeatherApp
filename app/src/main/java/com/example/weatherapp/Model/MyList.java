package com.example.weatherapp.Model;

import java.util.List;

public class MyList {
    private int dt;
    private Main main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private Rain rain;
    private Sys sys;
    private String dt_txt;

    public int getDt() {
        return dt;
    }

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }
}
