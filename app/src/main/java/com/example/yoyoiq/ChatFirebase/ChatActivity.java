package com.example.yoyoiq.ChatFirebase;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class ChatActivity extends AppCompatActivity {
    TextView sendBtn, close;
    EditText messageEd;
    RecyclerView recyclerView;
    String receiverId;
    String senderRoom, receiverRoom;
    DatabaseReference databaseReferenceSender, databaseReferenceReceiver;
    MessageAdapter messageAdapter;
    ArrayList<MessageModel> list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initMethod();
        setAction();

        receiverId = getIntent().getStringExtra("receiverId");
        senderRoom = FirebaseAuth.getInstance().getUid() + receiverId;
        receiverRoom = receiverId + FirebaseAuth.getInstance().getUid();
        databaseReferenceSender = FirebaseDatabase.getInstance().getReference().child("chats").child(senderRoom);
        databaseReferenceReceiver = FirebaseDatabase.getInstance().getReference().child("chats").child(receiverRoom);

        databaseReferenceSender.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MessageModel messageModel = dataSnapshot.getValue(MessageModel.class);
                    list.add(messageModel);
                    messageAdapter = new MessageAdapter(getApplicationContext(), list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
                    recyclerView.setAdapter(messageAdapter);
                    messageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initMethod() {
        sendBtn = findViewById(R.id.send);
        close = findViewById(R.id.close);
        messageEd = findViewById(R.id.message);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void setAction() {
        close.setOnClickListener(v -> finish());

        sendBtn.setOnClickListener(v -> {
            String message = messageEd.getText().toString();
            if (message.trim().length() > 0) {
                sendMessage(message);
            }
        });
    }

    private void sendMessage(String message) {
        String messageId = UUID.randomUUID().toString();
        MessageModel messageModel = new MessageModel(messageId, FirebaseAuth.getInstance().getUid(), message);

        list.add(messageModel);
        messageAdapter = new MessageAdapter(getApplicationContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
        recyclerView.setAdapter(messageAdapter);
        messageAdapter.notifyDataSetChanged();

        databaseReferenceSender
                .child(messageId)
                .setValue(messageModel);
    }
}