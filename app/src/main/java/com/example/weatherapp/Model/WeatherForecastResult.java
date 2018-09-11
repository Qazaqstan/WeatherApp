package com.example.weatherapp.Model;

import java.util.List;

public class WeatherForecastResult {
    private String cod;
    private double message;
    private int cnt;
    private List<MyList> list;
    private City city;

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public List<MyList> getList() {
        return list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
