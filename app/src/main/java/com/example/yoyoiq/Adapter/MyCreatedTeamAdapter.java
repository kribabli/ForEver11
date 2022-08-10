package com.example.yoyoiq.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Model.AllSelectedPlayer;
import com.example.yoyoiq.R;

import java.util.ArrayList;

public class MyCreatedTeamAdapter extends RecyclerView.Adapter<MyCreatedTeamAdapter.MyViewHolder> {
    Context context;
    ArrayList<AllSelectedPlayer> list;

    public MyCreatedTeamAdapter(Context context, ArrayList<AllSelectedPlayer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyCreatedTeamAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_created_team_list, parent, false);
        return new MyCreatedTeamAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCreatedTeamAdapter.MyViewHolder holder, int position) {
        holder.teamA.setText(list.get(position).getTitle());
        Log.d("Amit","Value 11"+list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView teamA, teamB, wkTv, batTv, arTv, bowlTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            teamA = itemView.findViewById(R.id.teamA);
            teamB = itemView.findViewById(R.id.teamB);
            wkTv = itemView.findViewById(R.id.wkTv);
            batTv = itemView.findViewById(R.id.batTv);
            arTv = itemView.findViewById(R.id.arTv);
            bowlTv = itemView.findViewById(R.id.bowlTv);
        }
    }
}
