package com.example.yoyoiq.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yoyoiq.Modal.TotalHomeData;
import com.example.yoyoiq.POJO.ItemsItem;
import com.example.yoyoiq.POJO.Teama;
import com.example.yoyoiq.POJO.Teamb;
import com.example.yoyoiq.R;

import java.util.ArrayList;

public class AllMatchAdapter extends RecyclerView.Adapter<AllMatchAdapter.MyViewHolder> {
    Context context;
    ArrayList<TotalHomeData> list;

    public AllMatchAdapter(Context context, ArrayList<TotalHomeData> list) {
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
        TotalHomeData titleList = list.get(position);
        holder.textViewTitle.setText(list.get(position).getTitle());

        holder.matchATv.setText(titleList.getName_a());
        Glide.with(context)
                .load(titleList.getLogo_url_a())
                .into(holder.matchAImage);

        holder.matchBTv.setText(titleList.getName_b());
        Glide.with(context)
                .load(titleList.getLogo_url_b())
                .into(holder.matchBImage);
    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle,matchATv, matchBTv;
        ImageView matchAImage,matchBImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.matchTitle);
            matchATv = itemView.findViewById(R.id.matcha);
            matchBTv = itemView.findViewById(R.id.matchb);
            matchAImage = itemView.findViewById(R.id.matchaImage);
            matchBImage = itemView.findViewById(R.id.matchbImage);
        }
    }
}
