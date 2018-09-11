package com.example.weatherapp.Model;

import java.util.ArrayList;

public class WeatherData {

    private Coord coord;
    private ArrayList<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private int dt;
    private Sys sys;
    private int id;
    private String name;
    private int cod;

    public ArrayList<Weather> getWeather() { return this.weather; }

    public String getBase() { return this.base; }

    public void setBase(String base) { this.base = base; }

    public Main getMain() { return this.main; }

    public int getVisibility() { return this.visibility; }

    public void setVisibility(int visibility) { this.visibility = visibility; }

    public int getDt() { return this.dt; }

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

}
