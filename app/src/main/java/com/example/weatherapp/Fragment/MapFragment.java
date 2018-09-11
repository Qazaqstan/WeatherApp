package com.example.weatherapp.Fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.Common.Common;
import com.example.weatherapp.Key.Key;
import com.example.weatherapp.Model.WeatherData;
import com.example.weatherapp.R;
import com.example.weatherapp.Retrofit.OpenWeatherMap;
import com.example.weatherapp.Retrofit.RetrofitClient;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MapFragment extends Fragment implements MapboxMap.OnMapClickListener, OnMapReadyCallback {

    public static MapFragment instance;

    private MapView mapView;
    private MapboxMap mapboxMap;

    private CardView cardView;
    private TextView dayOfWeek;
    private TextView temperature;
    private TextView description;
    private ImageView weatherImg;

    private CompositeDisposable compositeDisposable;
    private OpenWeatherMap service;

    public static MapFragment getInstance() {
        if (instance == null)
            instance = new MapFragment();
        return instance;
    }

    public MapFragment() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        service = retrofit.create(OpenWeatherMap.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.fragment_map, container, false);
        initUI(savedInstanceState, itemView);
        return itemView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
        compositeDisposable.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mapboxMap != null) {
            mapboxMap.removeOnMapClickListener(this);
        }
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMapClick(@NonNull LatLng point) {
        Common.location.setLongitude(point.getLongitude());
        Common.location.setLatitude(point.getLatitude());
        Common.isLocationChanged = true;
        getWeatherDataAndSetDataToViews();
        setCardViewVisibility();
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        mapboxMap.addOnMapClickListener(this);
        mapboxMap.setCameraPosition(setCameraPosition());
        getWeatherDataAndSetDataToViews();
        setCardViewVisibility();
    }

    private CameraPosition setCameraPosition() {
        return new CameraPosition.Builder()
                .target(new LatLng(Common.location.getLatitude(),Common.location.getLongitude()))
                .zoom(10)
                .build();
    }

    private void getWeatherDataAndSetDataToViews() {
        compositeDisposable.add(service.getWeatherByLatLon(
                String.valueOf(Common.location.getLatitude()),
                String.valueOf(Common.location.getLongitude()),
                Key.OpenWeatherMap,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherData>() {
                    @Override
                    public void accept(WeatherData weatherResult) throws Exception {
                        setDataToViews(weatherResult);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("ERROR", throwable.getMessage());
                    }
                })
        );
    }

    private void setCardViewVisibility() {
        new CountDownTimer(5000 ,1000) {
            public void onTick(long millisUntilFinished) {
                cardView.setVisibility(View.VISIBLE);
            }
            public void onFinish() {
                cardView.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    private void initUI(Bundle savedInstanceState, View itemView) {
        dayOfWeek = (TextView) itemView.findViewById(R.id.dayOfWeek);
        weatherImg = (ImageView) itemView.findViewById(R.id.weatherImg);
        temperature = (TextView) itemView.findViewById(R.id.temperature);
        description = (TextView) itemView.findViewById(R.id.description);
        cardView = (CardView) itemView.findViewById(R.id.cardView);
        mapView = (MapView) itemView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.setStyleUrl(Style.MAPBOX_STREETS);
        mapView.getMapAsync(this);
    }

    private void setDataToViews(WeatherData weatherResult) {
        Picasso.get()
                .load(weatherResult.getWeather().get(0).getIconURL())
                .into(weatherImg);

        dayOfWeek.setText(Common.convertUnixToDate(weatherResult.getDt()));
        description.setText(weatherResult.getName());
        temperature.setText(weatherResult.getMain().getTemp());
    }
}
