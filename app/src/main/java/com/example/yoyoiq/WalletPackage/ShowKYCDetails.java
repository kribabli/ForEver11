package com.example.yoyoiq.WalletPackage;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.common.SharedPrefManager;
import com.example.yoyoiq.R;
import com.example.yoyoiq.common.DatabaseConnectivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ShowKYCDetails extends AppCompatActivity {
    TextView mobileNo, emailId, panCardNo, bankAccountNo, backPress,mobileStatus,emailStatus,panStatus,accountStatus;
    SharedPrefManager sharedPrefManager;
    DatabaseConnectivity databaseConnectivity = new DatabaseConnectivity();
    String loggedInUserNumber, loggedInUserEmail;
    int status;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_kycdetails);
        sharedPreferences=getSharedPreferences("path", MODE_PRIVATE);
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
        mobileStatus = findViewById(R.id.mobileStatus);
        emailStatus = findViewById(R.id.emailStatus);
        panStatus = findViewById(R.id.panStatus);
        accountStatus = findViewById(R.id.accountStatus);
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
                                status= Integer.parseInt(dataSnapshot.child("status").getValue().toString());
                                mobileNo.setText(loggedInUserNumber);
                                emailId.setText(sharedPreferences.getString("emailId",""));
                                panCardNo.setText(panCard);
                                bankAccountNo.setText(accountNo);
                            }
                        }
                        if(status==1){
                            mobileStatus.setTextColor(Color.parseColor("#ECBD15"));
                            mobileStatus.setText("Pending");
                            emailStatus.setTextColor(Color.parseColor("#ECBD15"));
                            emailStatus.setText("Pending");
                            panStatus.setTextColor(Color.parseColor("#ECBD15"));
                            panStatus.setText("Pending");
                            accountStatus.setTextColor(Color.parseColor("#ECBD15"));
                            accountStatus.setText("Pending");


                        }
                        else if(status==2){
                            mobileStatus.setTextColor(Color.parseColor("#109E38"));
                            mobileStatus.setText("Verified");
                            emailStatus.setTextColor(Color.parseColor("#109E38"));
                            emailStatus.setText("Verified");
                            panStatus.setTextColor(Color.parseColor("#109E38"));
                            panStatus.setText("Verified");
                            accountStatus.setTextColor(Color.parseColor("#109E38"));
                            accountStatus.setText("Verified");

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}