package com.example.yoyoiq.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Model.AllSelectedPlayer;
import com.example.yoyoiq.R;
import com.example.yoyoiq.TeamPreviewActivity;

import java.util.ArrayList;
import java.util.List;

public class TeamPreviewAdapter extends RecyclerView.Adapter<TeamPreviewAdapter.MyViewHolder> {
    Context context;
    public List<AllSelectedPlayer> list;

    public TeamPreviewAdapter(Context context, ArrayList<AllSelectedPlayer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TeamPreviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_preview, parent, false);
        return new TeamPreviewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamPreviewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.setIsRecyclable(false);
        holder.playerName.setText(list.get(position).getTitle());
        holder.country.setText(list.get(position).getCountry() + " " + list.get(position).getPlaying_role());
        holder.playerPoints.setText(list.get(position).getPoints() + "pts");

        if (list.get(position).isCap()) {
            holder.tv_c.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.red_light)));
        } else {
        }

        if (list.get(position).isVcap()) {
            holder.tv_vc.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.red_light)));
        } else {
        }

        holder.tv_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamPreviewActivity.makeAllCaptainFalse(list.get(position).getPid());
                notifyDataSetChanged();
            }
        });

        holder.tv_vc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamPreviewActivity.makeAllViceCaptainFalse(list.get(position).getPid());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView playerName, country, playerPoints, tv_c, tv_vc;
        ImageView playerImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_c = itemView.findViewById(R.id.tv_c);
            tv_vc = itemView.findViewById(R.id.tv_vc);
            playerName = itemView.findViewById(R.id.playerName);
            country = itemView.findViewById(R.id.country);
            playerPoints = itemView.findViewById(R.id.playerPoints);
            playerImg = itemView.findViewById(R.id.playerImg);
        }
    }
}
