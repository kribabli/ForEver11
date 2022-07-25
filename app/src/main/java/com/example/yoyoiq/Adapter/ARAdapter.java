package com.example.yoyoiq.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Model.SquadsA;
import com.example.yoyoiq.R;

import java.util.ArrayList;

public class ARAdapter extends RecyclerView.Adapter<ARAdapter.MyViewHolder> {
    Context context;
    ArrayList<SquadsA> list;

    public ARAdapter(Context context, ArrayList<SquadsA> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ARAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_players, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ARAdapter.MyViewHolder holder, int position) {
        SquadsA listData = list.get(position);

        String fullPlayerName = listData.getName();
//        String playerFChar = fullPlayerName.substring(0, 1);
//        String[] separated = fullPlayerName.split(" ");
//        String separated1stChar = separated[0];
//        String separated2ndChar = separated[1];

        holder.playerName.setText(fullPlayerName);
        holder.playerCredit.setText(listData.getFantasy_player_rating());
        holder.country.setText(listData.getMatchAB());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView playerName, country, playerCredit;
        ImageView playerImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
            country = itemView.findViewById(R.id.country);
            playerCredit = itemView.findViewById(R.id.tv_PlayerCredit);
            playerImg = itemView.findViewById(R.id.playerImg);
        }
    }
}
