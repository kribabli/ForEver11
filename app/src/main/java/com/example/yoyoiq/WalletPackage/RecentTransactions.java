package com.example.yoyoiq.WalletPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.yoyoiq.R;

public class RecentTransactions extends AppCompatActivity {
    TextView backPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_transactions);

        backPress=findViewById(R.id.backPress);

        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}