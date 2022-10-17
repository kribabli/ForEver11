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
    }






}