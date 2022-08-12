package com.example.yoyoiq.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.CreatedTeamPOJO.CreatedTeamPOJOClass;
import com.example.yoyoiq.R;

import java.util.ArrayList;

public class MyCreatedTeamAdapter extends RecyclerView.Adapter<MyCreatedTeamAdapter.MyViewHolder> {
    Context context;
    ArrayList<CreatedTeamPOJOClass> list;
    int countWK=0;

    public MyCreatedTeamAdapter(Context context, ArrayList<CreatedTeamPOJOClass> list) {
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
        CreatedTeamPOJOClass createdTeamPOJOClass = list.get(position);

        if (createdTeamPOJOClass.isCap()==true){
            holder.teamA.setText(createdTeamPOJOClass.getTitle());
        }else if (createdTeamPOJOClass.isVcap()==true){
            holder.teamB.setText(createdTeamPOJOClass.getTitle());
        }

        for(int i=0;i<list.size();i++){
            if (createdTeamPOJOClass.getPlaying_role().equals("WK")){
                countWK++;
            }
            if (createdTeamPOJOClass.getPlaying_role().equals("BAT")){

            }
        }
        holder.wkTv.setText(""+countWK);
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
