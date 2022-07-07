package com.example.yoyoiq.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.R;

import java.util.ArrayList;

public class AllMatchAdapter extends RecyclerView.Adapter<AllMatchAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> list;

    public AllMatchAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AllMatchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMatchAdapter.MyViewHolder holder, int position) {
        String messageList = list.get(position);
        holder.textView.setText(messageList);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView, textView1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.matcha);
            textView1 = itemView.findViewById(R.id.matchb);
        }
    }
}
