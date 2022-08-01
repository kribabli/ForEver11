package com.example.yoyoiq.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Model.ContestsListPOJO;
import com.example.yoyoiq.R;

import java.util.ArrayList;

public class ContestsListAdapter extends RecyclerView.Adapter<ContestsListAdapter.MyViewHolder> {
    Context context;
    ArrayList<ContestsListPOJO> list;

    public ContestsListAdapter(Context context, ArrayList<ContestsListPOJO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ContestsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contest_list, parent, false);
        return new ContestsListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContestsListAdapter.MyViewHolder holder, int position) {
        ContestsListPOJO listData = list.get(position);
        holder.total_prize.setText(listData.getPrize_pool());
        holder.entryFee.setText(listData.getEntry());
        holder.totalSports.setText(listData.getTotal_team());
        holder.first_price.setText(listData.getFirst_price());
        holder.winningPer.setText(listData.getWinning_percentage());

        int totalS = Integer.parseInt(listData.getTotal_team());
        int leftS = Integer.parseInt(listData.getJoin_team());

        holder.leftSports.setText(String.valueOf(totalS - leftS));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView total_prize, entryFee, totalSports, leftSports,first_price,winningPer;
        CardView cardViewContest;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            total_prize = itemView.findViewById(R.id.total_prize);
            entryFee = itemView.findViewById(R.id.entryFee);
            totalSports = itemView.findViewById(R.id.totalSports);
            leftSports = itemView.findViewById(R.id.leftSports);
            first_price = itemView.findViewById(R.id.first_price);
            winningPer = itemView.findViewById(R.id.winningPer);
            cardViewContest = itemView.findViewById(R.id.cardViewContest);
        }
    }
}
