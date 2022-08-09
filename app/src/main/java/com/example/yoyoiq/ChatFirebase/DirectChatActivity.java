package com.example.yoyoiq.ChatFirebase;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Model.UserData;
import com.example.yoyoiq.R;
import com.example.yoyoiq.common.DatabaseConnectivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DirectChatActivity extends AppCompatActivity {
    ChatAdapter chatAdapter;
    TextView close;
    RecyclerView recyclerView;
    ArrayList<UserData> list = new ArrayList();
    DatabaseConnectivity databaseConnectivity = new DatabaseConnectivity();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_chat);
        initMethod();
        setAction();
        sharedPreferences = getSharedPreferences("path", MODE_PRIVATE);

        databaseConnectivity.getDatabasePath(this).child("RegisterDetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String uid = dataSnapshot.getKey();
                    String mobile1 = dataSnapshot.child("mobileNo").getValue().toString();
                    String password11 = dataSnapshot.child("password").getValue().toString();
                    String mobileS = sharedPreferences.getString("mobileNo", "");
                    String passwordS = sharedPreferences.getString("userPassword", "");

                    if (mobile1.contains(mobileS) && password11.contains(passwordS)) {
                        String userName = dataSnapshot.child("userName").getValue().toString();
                        String mobile = dataSnapshot.child("mobileNo").getValue().toString();
                        String emailId = dataSnapshot.child("emailId").getValue().toString();
                        String password = dataSnapshot.child("password").getValue().toString();

                        UserData userData = new UserData(userName, mobile, emailId, password);
                        list.add(userData);
                        chatAdapter = new ChatAdapter(getApplicationContext(), list);
                        recyclerView.setLayoutManager(new LinearLayoutManager(DirectChatActivity.this));
                        recyclerView.setAdapter(chatAdapter);
                        chatAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initMethod() {
        recyclerView = findViewById(R.id.recyclerView);
        close = findViewById(R.id.close);
    }

    private void setAction() {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}