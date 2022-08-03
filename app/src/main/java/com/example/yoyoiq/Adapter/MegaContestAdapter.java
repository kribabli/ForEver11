package com.example.yoyoiq.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.R;

public class MegaContestAdapter extends RecyclerView.Adapter<MegaContestAdapter.MyViewHolder> {

    @NonNull
    @Override
    public MegaContestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contest_winners_cardview, parent, false);
        return new MegaContestAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MegaContestAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
