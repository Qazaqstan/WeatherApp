package com.example.weatherapp.Model;

public class Main {
    private double temp;
    private double pressure;
    private double humidity;
    private double temp_min;
    private double temp_max;

    public Main() {
    }

    public String getTemp() { return this.temp + "°C"; }

    public void setTemp(double temp) { this.temp = temp; }

    public double getPressure() { return this.pressure; }

    public void setPressure(double pressure) { this.pressure = pressure; }

    public double getHumidity() { return this.humidity; }

    public void setHumidity(double humidity) { this.humidity = humidity; }

    public double getTempMin() { return this.temp_min; }

    public void setTempMin(double temp_min) { this.temp_min = temp_min; }

    public double getTempMax() { return this.temp_max; }

    public void setTempMax(double temp_max) { this.temp_max = temp_max; }
}
