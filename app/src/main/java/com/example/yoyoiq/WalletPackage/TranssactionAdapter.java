package com.example.yoyoiq.WalletPackage;


import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.R;
import com.example.yoyoiq.common.SessionManager;

import java.util.ArrayList;

public class TranssactionAdapter extends RecyclerView.Adapter<TranssactionAdapter.MyViewHolder> {
    Context context;
    ArrayList<transection> list;
    SessionManager sessionManager;

    public TranssactionAdapter(Context context, ArrayList<transection> list) {
        this.context = context;
        this.list = list;
        sessionManager = new SessionManager(context.getApplicationContext());
    }

    @NonNull
    @Override
    public TranssactionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_history_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TranssactionAdapter.MyViewHolder holder, int position) {
        if (list.get(position).getType().equalsIgnoreCase("credit")) {
            holder.signConvention.setText("+");
            holder.signConvention.setTextColor(Color.parseColor("#64e764"));

        } else {
            holder.signConvention.setText("-");
            holder.signConvention.setTextColor(Color.parseColor("#D70101"));
        }
        holder.symbol_rupees.setText(list.get(position).getAmount());
        holder.reason.setText("Deposited Cash");
        holder.layout.setOnClickListener(view -> {
            AlertDialog.Builder builder;
            AlertDialog alertDialog;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view1 = inflater.inflate(R.layout.show_transaction_details, null);
            TextView textView1 = view1.findViewById(R.id.transactionId);
            TextView textView2 = view1.findViewById(R.id.transactionDate);
            TextView textView3 = view1.findViewById(R.id.TeamName);

            textView1.setText("" + list.get(position).getTransection_id());
            textView2.setText("" + list.get(position).getCreated_date());
            textView3.setText("" + sessionManager.getUserData().getUserName());

            builder = new AlertDialog.Builder(context);
            builder.setView(view1);
            alertDialog = builder.create();
            alertDialog.show();
        });

        holder.expand_activities_button.setOnClickListener(view -> {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.show_transaction_details);
            TextView textView1 = dialog.findViewById(R.id.transactionId);
            TextView textView2 = dialog.findViewById(R.id.transactionDate);
            TextView textView3 = dialog.findViewById(R.id.TeamName);

            textView1.setText("" + list.get(position).getTransection_id());
            textView2.setText("" + list.get(position).getCreated_date());
            textView3.setText("" + sessionManager.getUserData().getUserName());
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView expand_activities_button;
        TextView reason;
        TextView symbol_rupees, signConvention;
        LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            expand_activities_button = (ImageView) itemView.findViewById(R.id.expand_activities_button);
            reason = (TextView) itemView.findViewById(R.id.reason);
            symbol_rupees = (TextView) itemView.findViewById(R.id.symbol_rupees);
            signConvention = (TextView) itemView.findViewById(R.id.signConvention);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
        }
    }
}
