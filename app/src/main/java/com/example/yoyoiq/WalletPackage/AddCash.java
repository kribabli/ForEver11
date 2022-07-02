package com.example.yoyoiq.WalletPackage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.PaymentOptions;
import com.example.yoyoiq.R;

public class AddCash extends AppCompatActivity {
    TextView addCash, backPress, myRecentPay, KYCDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cash2);

        addCash = findViewById(R.id.addCash);
        backPress = findViewById(R.id.backPress);
        myRecentPay = findViewById(R.id.myRecentPay);
        KYCDetails = findViewById(R.id.KYCDetails);

        myRecentPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCash.this, RecentTransactions.class);
                startActivity(intent);
            }
        });

        KYCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCash.this, KYCActivity.class);
                startActivity(intent);
            }
        });

        addCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCash.this, PaymentOptions.class);
                startActivity(intent);
            }
        });

        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}