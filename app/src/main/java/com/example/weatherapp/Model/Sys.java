package com.example.weatherapp.Model;

public class Sys {
    private int type;
    private int id;
    private double message;
    private String country;
    private int sunrise;
    private int sunset;

    public int getType() { return this.type; }

    public void setType(int type) { this.type = type; }

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    public double getMessage() { return this.message; }

    public void setMessage(double message) { this.message = message; }
}
