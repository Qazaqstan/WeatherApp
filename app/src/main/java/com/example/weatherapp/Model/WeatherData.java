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

    public WeatherData() {
    }

    public Coord getCoord() { return this.coord; }

    public void setCoord(Coord coord) { this.coord = coord; }

    public ArrayList<Weather> getWeather() { return this.weather; }

    public void setWeather(ArrayList<Weather> weather) { this.weather = weather; }

    public String getBase() { return this.base; }

    public void setBase(String base) { this.base = base; }

    public Main getMain() { return this.main; }

    public void setMain(Main main) { this.main = main; }

    public int getVisibility() { return this.visibility; }

    public void setVisibility(int visibility) { this.visibility = visibility; }

    public Wind getWind() { return this.wind; }

    public void setWind(Wind wind) { this.wind = wind; }

    public Clouds getClouds() { return this.clouds; }

    public void setClouds(Clouds clouds) { this.clouds = clouds; }

    public int getDt() { return this.dt; }

    public void setDt(int dt) { this.dt = dt; }

    public Sys getSys() { return this.sys; }

    public void setSys(Sys sys) { this.sys = sys; }

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public int getCod() { return this.cod; }

    public void setCod(int cod) { this.cod = cod; }

}
