package com.example.weatherapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weatherapp.Adapter.WeatherForecastAdapter;
import com.example.weatherapp.Common.Common;
import com.example.weatherapp.Key.Key;
import com.example.weatherapp.Model.WeatherForecastResult;
import com.example.weatherapp.R;
import com.example.weatherapp.Retrofit.OpenWeatherMap;
import com.example.weatherapp.Retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class ForecastFragment extends Fragment {

    private static ForecastFragment instance;

    private CompositeDisposable compositeDisposable;
    private OpenWeatherMap service;

    private TextView city;
    private RecyclerView listForecast;

    public static ForecastFragment getInstance() {
        if (instance == null)
            instance = new ForecastFragment();
        return instance;
    }

    public ForecastFragment() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        service = retrofit.create(OpenWeatherMap.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.fragment_forecast, container, false);
        city = (TextView) itemView.findViewById(R.id.city);
        listForecast = (RecyclerView) itemView.findViewById(R.id.listForecast);
        listForecast.setHasFixedSize(true);
        listForecast.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return itemView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        getForecastWeatherData();
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    private void getForecastWeatherData() {
        if (Common.isLocationChanged) {
            compositeDisposable.add(
                    service.getForecastWeatherByLatLon(
                            String.valueOf(Common.location.getLatitude()),
                            String.valueOf(Common.location.getLongitude()),
                            Key.OpenWeatherMap,
                            "metric"
                    )
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<WeatherForecastResult>() {
                                @Override
                                public void accept(WeatherForecastResult weatherForecastResult) {
                                    displayForecastWeather(weatherForecastResult);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) {
                                    Log.d("ERROR", throwable.getMessage());
                                }
                            })
            );
        }
        Common.isLocationChanged = false;
    }

    private void displayForecastWeather(WeatherForecastResult weatherForecastResult) {
        city.setText(weatherForecastResult.getCity().getName());
        WeatherForecastAdapter adapter = new WeatherForecastAdapter(getContext(), weatherForecastResult);
        listForecast.setAdapter(adapter);
    }
}
