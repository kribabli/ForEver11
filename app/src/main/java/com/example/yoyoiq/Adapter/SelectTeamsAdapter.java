package com.example.yoyoiq.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.InSideContestActivityFragments.myAllTeamRequest;
import com.example.yoyoiq.R;
import com.example.yoyoiq.common.SessionManager;

import java.util.ArrayList;

public class SelectTeamsAdapter extends RecyclerView.Adapter<SelectTeamsAdapter.MyViewHolder> {
    Context context;
    ArrayList<myAllTeamRequest> list;
    SessionManager sessionManager;

    public SelectTeamsAdapter(Context context, ArrayList<myAllTeamRequest> list) {
        this.context = context;
        this.list = list;
        sessionManager = new SessionManager(context.getApplicationContext());
    }

    @NonNull
    @Override
    public SelectTeamsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_team_list, parent, false);
        return new SelectTeamsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectTeamsAdapter.MyViewHolder holder, int position) {
        myAllTeamRequest allTeamRequest = list.get(position);
        if (list.size() > 0) {
            holder.CName.setText(allTeamRequest.getCaptain());
            holder.VCname.setText(allTeamRequest.getVicecaptain());
            holder.wkTv.setText(String.valueOf(allTeamRequest.getWkeeper()));
            holder.batTv.setText(String.valueOf(allTeamRequest.getBatsman()));
            holder.arTv.setText(String.valueOf(allTeamRequest.getAllrounder()));
            holder.bowlTv.setText(String.valueOf(allTeamRequest.getBoller()));
            holder.teamACount.setText(String.valueOf(allTeamRequest.getTeamAcount()));
            holder.teamBCount.setText(String.valueOf(allTeamRequest.getTeamBcount()));
            holder.userNameAndTid.setText(sessionManager.getUserData().getUserName() + "(T" + (position + 1) + ")");
            holder.teamA.setText(allTeamRequest.getTeamAName());
            holder.teamB.setText(allTeamRequest.getTeamBName());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView teamA, teamB, wkTv, batTv, arTv, bowlTv, CName, VCname, teamACount, teamBCount, userNameAndTid;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            teamA = itemView.findViewById(R.id.teamA);
            teamB = itemView.findViewById(R.id.teamB);
            wkTv = itemView.findViewById(R.id.wkTv);
            batTv = itemView.findViewById(R.id.batTv);
            arTv = itemView.findViewById(R.id.arTv);
            bowlTv = itemView.findViewById(R.id.bowlTv);
            CName = itemView.findViewById(R.id.CName);
            VCname = itemView.findViewById(R.id.VCname);
            teamACount = itemView.findViewById(R.id.teamACount);
            teamBCount = itemView.findViewById(R.id.teamBCount);
            userNameAndTid = itemView.findViewById(R.id.userNameAndTid);
        }
    }
}