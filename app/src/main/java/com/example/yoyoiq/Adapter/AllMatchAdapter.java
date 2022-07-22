package com.example.yoyoiq.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yoyoiq.ContestActivity;
import com.example.yoyoiq.Model.TotalHomeData;
import com.example.yoyoiq.R;

import java.util.ArrayList;

public class AllMatchAdapter extends RecyclerView.Adapter<AllMatchAdapter.MyViewHolder> {
    Context context;
    ArrayList<TotalHomeData> list;
    //This Adapter is use for MatchList(Means Upcoming Match)

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
    public void onBindViewHolder(@NonNull AllMatchAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TotalHomeData listData = list.get(position);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ContestActivity.class);
                intent.putExtra("shortNameA", listData.getShort_name_a());
                intent.putExtra("shortNameB", listData.getShort_name_b());
                intent.putExtra("match_id", listData.getMatch_id());
                intent.putExtra("logo_url_a", listData.getLogo_url_a());
                intent.putExtra("logo_url_b", listData.getLogo_url_b());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.textViewTitle.setText(list.get(position).getTitle());

        holder.matchATv.setText(listData.getName_a());
        holder.shortNameA.setText(listData.getShort_name_a());
        Glide.with(context)
                .load(listData.getLogo_url_a())
                .into(holder.matchAImage);

        holder.matchBTv.setText(listData.getName_b());
        holder.shortNameB.setText(listData.getShort_name_b());
        Glide.with(context)
                .load(listData.getLogo_url_b())
                .into(holder.matchBImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, matchATv, matchBTv, shortNameA, shortNameB;
        ImageView matchAImage, matchBImage;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.matchTitle);
            matchATv = itemView.findViewById(R.id.matcha);
            matchBTv = itemView.findViewById(R.id.matchb);
            shortNameA = itemView.findViewById(R.id.shortNameA);
            shortNameB = itemView.findViewById(R.id.shortNameB);
            matchAImage = itemView.findViewById(R.id.matchaImage);
            matchBImage = itemView.findViewById(R.id.matchbImage);
            cardView = itemView.findViewById(R.id.cardViewMatchList);
        }
    }
}
