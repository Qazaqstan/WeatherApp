package com.example.weatherapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.Common.Common;
import com.example.weatherapp.Model.WeatherForecastResult;
import com.example.weatherapp.R;
import com.squareup.picasso.Picasso;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.MyViewHolder> {

    private Context context;
    private WeatherForecastResult weatherForecastResult;

    public WeatherForecastAdapter(Context context, WeatherForecastResult weatherForecastResult) {
        this.context = context;
        this.weatherForecastResult = weatherForecastResult;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_weather_forecast, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get()
                .load(getImgURL(position))
                .into(holder.weatherImg);

        setDataToView(holder, position);
    }

    @Override
    public int getItemCount() {
        return weatherForecastResult.getList().size();
    }

    private void setDataToView(MyViewHolder holder, int position) {
        holder.date.setText(
                new StringBuilder(Common.convertUnixToDate(weatherForecastResult.getList().get(position).getDt()))
        );

        holder.description.setText(
                new StringBuilder(weatherForecastResult.getList().get(position).getWeather().get(0).getDescription())
        );

        holder.temperature.setText(
                new StringBuilder(
                        String.valueOf(weatherForecastResult.getList().get(position).getMain().getTemp())
                )
                        .append("°C")
        );
    }

    private String getImgURL(int position) {
        return new StringBuilder("https://openweathermap.org/img/w/")
                .append(weatherForecastResult.getList().get(position).getWeather().get(0).getIcon())
                .append(".png")
                .toString();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView description;
        TextView temperature;

        ImageView weatherImg;

        public MyViewHolder(View itemView) {
            super(itemView);

            weatherImg = (ImageView) itemView.findViewById(R.id.weatherImg);
            date = (TextView) itemView.findViewById(R.id.date);
            description = (TextView) itemView.findViewById(R.id.description);
            temperature = (TextView) itemView.findViewById(R.id.temperature);

        }
    }
}
