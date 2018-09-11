package com.example.weatherapp.Model;

public class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    public String getIconURL() { return "https://openweathermap.org/img/w/" + this.icon + ".png"; }

    public void setIcon(String icon) { this.icon = icon; }

}
