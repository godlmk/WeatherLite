package com.weather.lite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.lite.R;
import com.weather.lite.model.City;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private List<City> dataList;

    private OnItemClickListener listener;

    public CityAdapter(List<City> dataList, OnItemClickListener listener) {
        this.dataList = dataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        City city = dataList.get(position);
        holder.tvCity.setText(city.getName());
        holder.tvAdcode.setText(city.getAdcode());
        holder.itemView.setOnClickListener(view -> listener.onItemClick(position,city));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCity;
        TextView tvAdcode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCity = itemView.findViewById(R.id.tv_city);
            tvAdcode = itemView.findViewById(R.id.tv_adcode);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position, City data);
    }

}