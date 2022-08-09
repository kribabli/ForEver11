package com.example.yoyoiq.ChatFirebase;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    private Context context;
    private List<MessageModel> list;

    public MessageAdapter(Context context, ArrayList<MessageModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_row, parent, false);
        return new MessageAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MyViewHolder holder, int position) {
        MessageModel messageModel = list.get(position);
        holder.message.setText("" + messageModel.getMessage());
        if (messageModel.getSenderId() != null) {
            if (FirebaseAuth.getInstance().getUid() != null) {
                if (!messageModel.getSenderId().equals(FirebaseAuth.getInstance().getUid())) {
                    holder.main.setBackgroundColor(context.getResources().getColor(R.color.text_bg1));
                } else {
                    holder.main.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        LinearLayout main;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message);
            main = itemView.findViewById(R.id.mainMessageLayout);
        }
    }
}
