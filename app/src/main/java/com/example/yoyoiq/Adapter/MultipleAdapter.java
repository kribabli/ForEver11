package com.example.yoyoiq.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Model.SquadsA;
import com.example.yoyoiq.R;

import java.util.ArrayList;

public class MultipleAdapter extends RecyclerView.Adapter<MultipleAdapter.MyViewHolder> {
    Context context;
    ArrayList<SquadsA> list;

    public MultipleAdapter(Context context, ArrayList<SquadsA> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MultipleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_players, parent, false);
        return new MultipleAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SquadsA model = list.get(position);
        holder.playerName.setText(model.getShort_namePlayers());
//        holder.view.setBackgroundColor(model.isSelected() ? Color.CYAN : Color.WHITE);
        holder.country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                model.setSelected(!model.isSelected());
//                holder.itemView.setBackgroundColor(model.isSelected() ? Color.CYAN : Color.WHITE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView playerName, country, playerCredit;
        ImageView playerImg;
        private View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            playerName = itemView.findViewById(R.id.playerName);
            country = itemView.findViewById(R.id.country);
            playerCredit = itemView.findViewById(R.id.tv_PlayerCredit);
            playerImg = itemView.findViewById(R.id.playerImg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Position", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
