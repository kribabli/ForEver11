package com.example.yoyoiq.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Model.AllSelectedPlayer;
import com.example.yoyoiq.Model.SquadsA;
import com.example.yoyoiq.R;
import com.example.yoyoiq.common.HelperData;

import java.util.ArrayList;
import java.util.Collections;

public class BOWLAdapter extends RecyclerView.Adapter<BOWLAdapter.MyViewHolder> {
    Context context;
    ArrayList<SquadsA> list;
    boolean isEnable = false;
    private int lastSelectedPosition = -1;

    public BOWLAdapter(Context context, ArrayList<SquadsA> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BOWLAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_players, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BOWLAdapter.MyViewHolder holder, int position) {
        SquadsA listData = list.get(position);
        String fullPlayerName = listData.getName();
        holder.playerName.setText(listData.getShort_namePlayers());
        holder.playerCredit.setText(listData.getFantasy_player_rating());
        holder.country.setText(listData.getAbbr());

        holder.cardViewSelected.setOnClickListener(view -> {
            if (!isEnable) {
                listData.setSelected(!listData.isSelected());
                String credit = listData.getFantasy_player_rating();
                int pid= Integer.parseInt(listData.getPidPlayers());
                double playerCredit= Double.parseDouble(credit);
                holder.cardViewSelected.setBackgroundColor(listData.isSelected() ? Color.LTGRAY : Color.WHITE);
                if(holder.alreadyAddedPlayer.getVisibility()==View.VISIBLE){
                    holder.alreadyAddedPlayer.setVisibility(View.GONE);
                    Log.d("Amit","Check here ");
                    HelperData.bowl.setValue(HelperData.wk.getValue() - 1);
                    HelperData.creditCounter.setValue(Double.valueOf(HelperData.creditCounter.getValue() - playerCredit ));
                    HelperData.playerCounter.setValue(HelperData.playerCounter.getValue() - 1);
                    if(HelperData.team1NameShort==listData.getAbbr()){
                        HelperData.conty1.setValue(HelperData.conty1.getValue()-1);}
                    else if(HelperData.team2NameShort==listData.getAbbr()){
                        HelperData.conty2.setValue(HelperData.conty2.getValue()-1);}
                }
                else{
                    holder.alreadyAddedPlayer.setVisibility(View.VISIBLE);
                    holder.im_AddPlayer.setVisibility(View.VISIBLE);
                    HelperData.bowl.setValue(HelperData.wk.getValue() + 1);
                    HelperData.creditCounter.setValue(Double.valueOf(HelperData.creditCounter.getValue() + playerCredit ));
                    HelperData.playerCounter.setValue(HelperData.playerCounter.getValue() + 1);
                    if(HelperData.team1NameShort==listData.getAbbr()){
                        HelperData.conty1.setValue(HelperData.conty1.getValue()+1);}
                    else if(HelperData.team2NameShort==listData.getAbbr()){
                        HelperData.conty2.setValue(HelperData.conty2.getValue()+1);}
                    AllSelectedPlayer allSelectedPlayer=new AllSelectedPlayer(pid, listData.getShort_namePlayers(), listData.getAbbr(),"WK",playerCredit,false,false,false,"");
                    HelperData.allSelectedPlayer.setValue(Collections.singletonList(allSelectedPlayer));
                }
                lastSelectedPosition = holder.getAdapterPosition();
            } else {
                listData.setSelected(listData.isSelected());
                holder.cardViewSelected.setBackgroundColor(listData.isSelected() ? Color.LTGRAY : Color.WHITE);
                holder.im_AddPlayer.setVisibility(View.VISIBLE);
                Log.d("Amit","Check ");
                if(holder.alreadyAddedPlayer.getVisibility()==View.VISIBLE){
                    holder.alreadyAddedPlayer.setVisibility(View.GONE);
                }
                lastSelectedPosition = holder.getAdapterPosition();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView playerName, country, playerCredit;
        ImageView playerImg,alreadyAddedPlayer, im_AddPlayer;
        CardView cardViewSelected;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
            country = itemView.findViewById(R.id.country);
            playerCredit = itemView.findViewById(R.id.tv_PlayerCredit);
            playerImg = itemView.findViewById(R.id.playerImg);
            im_AddPlayer = itemView.findViewById(R.id.im_AddPlayer);
            cardViewSelected = itemView.findViewById(R.id.cardViewSelected);
            alreadyAddedPlayer = itemView.findViewById(R.id.alreadyAddedPlayer);
        }
    }
}
