package com.example.weatherapp.Retrofit;

import com.example.weatherapp.Model.WeatherData;
import com.example.weatherapp.Model.WeatherForecastResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMap {

    @GET("weather")
    Observable<WeatherData> getWeatherByLatLon(@Query("lat") String lat,
                                               @Query("lon") String lon,
                                               @Query("appid") String appid,
                                               @Query("units") String unit);

    @GET("forecast")
    Observable<WeatherForecastResult> getForecastWeatherByLatLon(@Query("lat") String lat,
                                                                 @Query("lon") String lon,
                                                                 @Query("appid") String appid,
                                                                 @Query("units") String unit);
}
