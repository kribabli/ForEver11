package com.example.yoyoiq.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Model.PriceContributionPOJO;
import com.example.yoyoiq.R;

import java.util.ArrayList;

public class PriceListAdapter extends RecyclerView.Adapter<PriceListAdapter.MyViewHolder> {
    Context context;
    ArrayList<PriceContributionPOJO> list;

    public PriceListAdapter(Context context, ArrayList<PriceContributionPOJO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PriceListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.winning_list, parent, false);
        return new PriceListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceListAdapter.MyViewHolder holder, int position) {
        PriceContributionPOJO listData = list.get(position);
        holder.rank.setText(String.valueOf("# " + listData.getI()));
        holder.price.setText(String.valueOf(listData.getPosition()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rank, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.rank);
            price = itemView.findViewById(R.id.price);
        }
    }
}
