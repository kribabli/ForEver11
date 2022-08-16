package com.example.yoyoiq.OnlyTeamPreView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.R;

import java.util.ArrayList;

public class OnlyTeamPreviewAdapter extends RecyclerView.Adapter<OnlyTeamPreviewAdapter.MyViewHolder> {
    Context context;
    ArrayList<OnlyTeamPreviewModel> list;

    public OnlyTeamPreviewAdapter(Context context, ArrayList<OnlyTeamPreviewModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OnlyTeamPreviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onlyteam_preview_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnlyTeamPreviewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        OnlyTeamPreviewModel onlyTeamPreviewModel = list.get(position);
        holder.setIsRecyclable(false);
        holder.playerName.setText(onlyTeamPreviewModel.getWkName());
        holder.playerPts.setText(onlyTeamPreviewModel.getCaptain());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView playerName, playerPts;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
            playerPts = itemView.findViewById(R.id.playerPts);
        }
    }
}
