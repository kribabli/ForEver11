package com.example.yoyoiq.WalletPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.yoyoiq.R;

public class UPIActivity extends AppCompatActivity {
    EditText upiId;
    ProgressBar progressBar;
    Button proceed;
    String balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upiactivity);
        initMethod();
        setAction();
    }

    private void initMethod() {
        progressBar = findViewById(R.id.idTVTransactionDetails);
        upiId = findViewById(R.id.upiId);
        proceed = findViewById(R.id.proceed);
        balance = getIntent().getStringExtra("amount");
    }


    private void setAction() {
        proceed.setOnClickListener(view -> {
            validationCheck();
        });
    }

    private boolean validationCheck() {
        boolean isValid = true;
        try {
            if (upiId.getText().toString().trim().length() == 0
                    || upiId.getText().toString().startsWith("0")
                    || upiId.getText().toString().startsWith("00")) {
                upiId.setError("Please enter correct UPI Id");
                upiId.requestFocus();
                isValid = false;
            } else {
                UpiPaymentMethod(balance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }

    private void UpiPaymentMethod(String balance) {
    }

}
    
