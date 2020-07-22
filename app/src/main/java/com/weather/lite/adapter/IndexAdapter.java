package com.weather.lite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.lite.R;
import com.weather.lite.model.Index;

import java.util.List;

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHoder>{
    private List<Index> dataList;

    public IndexAdapter(List<Index> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_index
        ,parent,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        Index index=dataList.get(position);
        holder.tvName.setText(index.getName());
        holder.tvCategory.setText(index.getCategory());
        holder.tvText.setText(index.getText());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHoder extends RecyclerView.ViewHolder {

        TextView tvName;

        TextView tvText;

        TextView tvCategory;


        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_name);
            tvCategory=itemView.findViewById(R.id.tv_category);
            tvText=itemView.findViewById(R.id.tv_text);
        }
    }
}
