package com.example.yoyoiq.WalletPackage;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.Modal.SharedPrefManager;
import com.example.yoyoiq.R;
import com.example.yoyoiq.common.DatabaseConnectivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ShowKYCDetails extends AppCompatActivity {
    TextView mobileNo, emailId, panCardNo, bankAccountNo, backPress;
    SharedPrefManager sharedPrefManager;
    DatabaseConnectivity databaseConnectivity = new DatabaseConnectivity();
    String loggedInUserNumber, loggedInUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_kycdetails);
        initMethod();
        setAction();
        showKYCDetails();
    }

    private void initMethod() {
        backPress = findViewById(R.id.backPress);
        mobileNo = findViewById(R.id.mobileNo);
        emailId = findViewById(R.id.email);
        panCardNo = findViewById(R.id.panCardNumber);
        bankAccountNo = findViewById(R.id.bankAccountNo);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        loggedInUserNumber = sharedPrefManager.getUserData().getMobileNo();
        loggedInUserEmail = sharedPrefManager.getUserData().getEmailId();
    }

    private void setAction() {

        backPress.setOnClickListener(view -> onBackPressed());
    }

    private void showKYCDetails() {
        databaseConnectivity.getDatabasePath(ShowKYCDetails.this).child("KYCDetails")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            if (dataSnapshot.getKey().equals(loggedInUserNumber)) {
                                String panCard = dataSnapshot.child("panCard").getValue().toString();
                                String accountNo = dataSnapshot.child("accountNo").getValue().toString();
                                mobileNo.setText(loggedInUserNumber);
                                emailId.setText(loggedInUserEmail);
                                panCardNo.setText(panCard);
                                bankAccountNo.setText(accountNo);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}