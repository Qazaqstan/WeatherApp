package com.example.weatherapp.Model;

public class Coord {
    private double lon;
    private double lat;

    public Coord() {
    }

    public double getLon() { return this.lon; }

    public void setLon(double lon) { this.lon = lon; }

    public double getLat() { return this.lat; }

    public void setLat(double lat) { this.lat = lat; }

    @Override
    public String toString() {
        return "[" + lat + ", " + lon + "]";
    }
}
