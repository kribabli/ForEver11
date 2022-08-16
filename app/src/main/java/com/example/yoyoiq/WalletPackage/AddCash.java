package com.example.yoyoiq.WalletPackage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.KYC.KYCActivity;
import com.example.yoyoiq.KYC.ShowKYCDetails;
import com.example.yoyoiq.NotificationActivity;
import com.example.yoyoiq.R;
import com.example.yoyoiq.common.DatabaseConnectivity;
import com.example.yoyoiq.common.HelperData;
import com.example.yoyoiq.common.SharedPrefManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddCash extends AppCompatActivity implements PaymentResultListener {
    TextView addCash, backPress, myRecentPay, KYCDetails, notification;
    EditText amount;
    DatabaseConnectivity databaseConnectivity = new DatabaseConnectivity();
    String loggedInUserNumber;
    SharedPrefManager sharedPrefManager;
    List<String> checkId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cash2);
        Checkout.preload(getApplicationContext());
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        initMethod();
        loggedInUserNumber = sharedPrefManager.getUserData().getMobileNo();


        databaseConnectivity.getDatabasePath(AddCash.this).child("KYCDetails")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                String key = ds.getKey();
                                checkId.add(key);
                            }
                        }
                        KYCDetails.setOnClickListener(view -> {
                            if (checkId.contains(loggedInUserNumber)) {
                                Intent intent = new Intent(AddCash.this, ShowKYCDetails.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent1 = new Intent(AddCash.this, KYCActivity.class);
                                startActivity(intent1);
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

        setAction();
    }

    private void initMethod() {
        addCash = findViewById(R.id.addCash);
        amount = findViewById(R.id.amount);
        notification = findViewById(R.id.notification);
        backPress = findViewById(R.id.backPress);
        myRecentPay = findViewById(R.id.myRecentPay);
        KYCDetails = findViewById(R.id.KYCDetails);
    }

    private void setAction() {
        myRecentPay.setOnClickListener(view -> {
            Intent intent = new Intent(AddCash.this, RecentTransactions.class);
            startActivity(intent);
        });

        backPress.setOnClickListener(view -> onBackPressed());

        notification.setOnClickListener(view -> {
            Intent intent = new Intent(AddCash.this, NotificationActivity.class);
            startActivity(intent);
        });

        addCash.setOnClickListener(view -> addAmountValidation());
    }

    private boolean addAmountValidation() {
        boolean isValid = true;
        try {
            if (amount.getText().toString().trim().length() == 0
                    || amount.getText().toString().startsWith("0")
                    || amount.getText().toString().startsWith("00")) {
                amount.setError("Please enter amount");
                amount.requestFocus();
                isValid = false;
            } else {
                paymentMethod();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }

    private void paymentMethod() {
        String sAmount =amount.getText().toString();
        int amount = Math.round(Float.parseFloat(sAmount) * 100);

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_jBetacTbV0YhJN");
        checkout.setImage(R.drawable.cricket_player);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", "YoYoIq");
            jsonObject.put("description", "Test Payment");
            jsonObject.put("theme.color", "#0093DD");
            jsonObject.put("currency", "INR");
            jsonObject.put("amount", amount);
            jsonObject.put("contact", "xxxxxxxx32");
            jsonObject.put("meratemplate", "mtteam.suraj@gmail.com");
            checkout.open(AddCash.this, jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Payment Success..");
        builder.setMessage(s);
        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}