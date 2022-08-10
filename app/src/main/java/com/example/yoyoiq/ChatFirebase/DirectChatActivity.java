package com.example.yoyoiq.ChatFirebase;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_chat);
        initMethod();
        setAction();
        getAllUserList();
    }

    private void initMethod() {
        recyclerView = findViewById(R.id.recyclerView);
        close = findViewById(R.id.close);
        swipeRefreshLayout = findViewById(R.id.swiper);
    }

    private void setAction() {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllUserList();
            }
        });
    }

    private void getAllUserList() {
        databaseConnectivity.getDatabasePath(this).child("RegisterDetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
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
                    swipeRefreshLayout.setRefreshing(false);
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}