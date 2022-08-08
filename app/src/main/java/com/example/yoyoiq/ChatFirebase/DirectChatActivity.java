package com.example.yoyoiq.ChatFirebase;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.R;

public class DirectChatActivity extends AppCompatActivity {
    TextView send, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_chat);
        send = findViewById(R.id.send);
        message = findViewById(R.id.message);

    }
}