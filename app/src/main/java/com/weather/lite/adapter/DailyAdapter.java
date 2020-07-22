package com.weather.lite.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.lite.R;
import com.weather.lite.model.Daily;

import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHoder> {

    private List<Daily> dataList;

    public DailyAdapter(List<Daily> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily, parent,
                false);
        return new ViewHoder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        Daily daily = dataList.get(position);
        holder.tvDate.setText(daily.getDate());
        holder.tvTextDay.setText(daily.getTextDay());
        holder.tvTextNight.setText(daily.getTextNight());
        holder.tvHumidity.setText(daily.getHumidity() + "%");
        holder.tvWindScale.setText(daily.getWindScaleDay() + "级");
        holder.tvWindDir.setText(daily.getWindDirDay());
        holder.tvTempMin.setText("最低" + daily.getTempMin() + "℃");
        holder.tvTempMax.setText("最高" + daily.getTempMax() + "℃");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHoder extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvTextDay;
        TextView tvTextNight;
        TextView tvHumidity;
        TextView tvWindScale;
        TextView tvWindDir;
        TextView tvTempMin;
        TextView tvTempMax;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTextDay = itemView.findViewById(R.id.tv_text_day);
            tvTextNight = itemView.findViewById(R.id.tv_text_night);
            tvHumidity = itemView.findViewById(R.id.tv_humidity);
            tvWindScale = itemView.findViewById(R.id.tv_wind_scale);
            tvWindDir = itemView.findViewById(R.id.tv_wind_dir);
            tvTempMax = itemView.findViewById(R.id.tv_temp_max);
            tvTempMin = itemView.findViewById(R.id.tv_temp_min);
        }
    }

}
