package com.example.yoyoiq.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Model.AllSelectedPlayer;
import com.example.yoyoiq.R;

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
    public void onBindViewHolder(@NonNull TeamPreviewAdapter.MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.playerName.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView playerName;
        ImageView playerImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
            playerImg = itemView.findViewById(R.id.playerImg);
        }
    }
}
