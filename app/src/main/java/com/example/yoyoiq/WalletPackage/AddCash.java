package com.example.yoyoiq.WalletPackage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.Modal.SharedPrefManager;
import com.example.yoyoiq.NotificationActivity;
import com.example.yoyoiq.R;
import com.example.yoyoiq.common.DatabaseConnectivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class AddCash extends AppCompatActivity implements PaymentResultListener {
    TextView addCash, backPress, myRecentPay, KYCDetails, notification;
    EditText amount;
    DatabaseConnectivity databaseConnectivity = new DatabaseConnectivity();
    String loggedInUserNumber;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cash2);
        Checkout.preload(getApplicationContext());
        initMethod();
        setAction();
    }

    private void initMethod() {
        addCash = findViewById(R.id.addCash);
        amount = findViewById(R.id.amount);
        notification = findViewById(R.id.notification);
        backPress = findViewById(R.id.backPress);
        myRecentPay = findViewById(R.id.myRecentPay);
        KYCDetails = findViewById(R.id.KYCDetails);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        loggedInUserNumber = sharedPrefManager.getUserData().getMobileNo();
    }

    private void setAction() {
        myRecentPay.setOnClickListener(view -> {
            Intent intent = new Intent(AddCash.this, RecentTransactions.class);
            startActivity(intent);
        });

        KYCDetails.setOnClickListener(view -> checkKYCDoneOrNot());

        backPress.setOnClickListener(view -> onBackPressed());

        notification.setOnClickListener(view -> {
            Intent intent = new Intent(AddCash.this, NotificationActivity.class);
            startActivity(intent);
        });

        addCash.setOnClickListener(view -> {
//                Intent intent = new Intent(AddCash.this, PaymentOptions.class);
//                intent.putExtra("amount", amount.getText().toString());
//                startActivity(intent);
            addAmountValidation();
        });
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
        String sAmount = "100";
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

    public void checkKYCDoneOrNot() {
        databaseConnectivity.getDatabasePath(AddCash.this).child("KYCDetails")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            if (dataSnapshot.getKey().equals(loggedInUserNumber)) {
                                Intent intent = new Intent(AddCash.this, ShowKYCDetails.class);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(AddCash.this, KYCActivity.class);
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }
}