package com.example.yoyoiq.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Model.SquadsA;
import com.example.yoyoiq.R;

import java.util.ArrayList;

public class SquadsAAdapter extends RecyclerView.Adapter<SquadsAAdapter.MyViewHolder> {
    Context context;
    ArrayList<SquadsA> list;
    boolean isEnable = false;
    private int lastSelectedPosition = -1;

    public SquadsAAdapter(Context context, ArrayList<SquadsA> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SquadsAAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_players, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SquadsAAdapter.MyViewHolder holder, int position) {
        SquadsA listData = list.get(position);

        String fullPlayerName = listData.getName();
//        String playerFChar = fullPlayerName.substring(0, 1);
//        String[] separated = fullPlayerName.split(" ");
//        String separated1stChar = separated[0];
//        String separated2ndChar = separated[1];

        holder.playerName.setText(listData.getShort_namePlayers());
        holder.playerCredit.setText(listData.getFantasy_player_rating());
        holder.country.setText(listData.getAbbr());

        holder.cardViewSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEnable) {
                    listData.setSelected(!listData.isSelected());
                    String credit = listData.getFantasy_player_rating();

                    holder.cardViewSelected.setBackgroundColor(listData.isSelected() ? Color.LTGRAY : Color.WHITE);
                    holder.im_AddPlayer.setVisibility(View.GONE);
                    holder.alreadyAddedPlayer.setVisibility(View.VISIBLE);
                    lastSelectedPosition = holder.getAdapterPosition();
                } else {
                    listData.setSelected(listData.isSelected());
                    holder.cardViewSelected.setBackgroundColor(listData.isSelected() ? Color.LTGRAY : Color.WHITE);
                    holder.im_AddPlayer.setVisibility(View.VISIBLE);
                    holder.alreadyAddedPlayer.setVisibility(View.GONE);
                    lastSelectedPosition = holder.getAdapterPosition();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView playerName, country, playerCredit;
        ImageView playerImg, alreadyAddedPlayer, im_AddPlayer;
        CardView cardViewSelected;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
            country = itemView.findViewById(R.id.country);
            playerCredit = itemView.findViewById(R.id.tv_PlayerCredit);
            playerImg = itemView.findViewById(R.id.playerImg);
            alreadyAddedPlayer = itemView.findViewById(R.id.alreadyAddedPlayer);
            im_AddPlayer = itemView.findViewById(R.id.im_AddPlayer);
            cardViewSelected = itemView.findViewById(R.id.cardViewSelected);
        }
    }
}
