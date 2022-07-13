package com.example.yoyoiq;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentOptions extends AppCompatActivity {
    TextView backPress, passAmount;
    String amount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);

        passAmount = findViewById(R.id.passAmount);
        amount = getIntent().getStringExtra("amount");
        passAmount.setText(amount);

        backPress = findViewById(R.id.backPress);
        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}