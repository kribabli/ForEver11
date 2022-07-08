package com.example.yoyoiq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.Modal.SharedPrefManager;
import com.example.yoyoiq.Modal.UserData;
import com.example.yoyoiq.common.DatabaseConnectivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    Button sign_In_FB;
    EditText mobileNo, userPassword;
    TextView login, backPress;
    DatabaseConnectivity databaseConnectivity = new DatabaseConnectivity();
    String password, alreadyRegisterMobile;
    SharedPrefManager sharedPrefManager;
    ArrayList<String> allPhoneNumber = new ArrayList<>();
    ArrayList<String> allPassword = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        initMethod();
        setAction();
        DownloadData();
    }

    private void initMethod() {
        sign_In_FB = findViewById(R.id.button_facebook);
        mobileNo = findViewById(R.id.mobileNo);
        userPassword = findViewById(R.id.userPassword);
        login = findViewById(R.id.login);
        backPress = findViewById(R.id.backPress);
    }

    private void setAction() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataValidation();
            }
        });
        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void DownloadData() {
        databaseConnectivity.getDatabasePath(this).child("RegisterDetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        alreadyRegisterMobile = dataSnapshot.child("mobileNo").getValue().toString();
                        allPhoneNumber.add(alreadyRegisterMobile);
                        password = dataSnapshot.child("password").getValue().toString();
                        allPassword.add(password);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private boolean dataValidation() {
        boolean isValid = true;
        try {
            if (mobileNo.getText().toString().trim().length() == 0) {
                mobileNo.setError("Please enter mobile number");
                mobileNo.requestFocus();
                isValid = false;
            } else if (userPassword.getText().toString().trim().length() == 0) {
                userPassword.setError("Please enter password");
                userPassword.requestFocus();
                isValid = false;
            } else {
                LoginValidation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }

    private void LoginValidation() {
        String mobile = mobileNo.getText().toString().trim();
        String password1 = userPassword.getText().toString().trim();
        String userName = "";
        String emailId = "";
        UserData userData = new UserData(userName, mobile, emailId, password1);
        sharedPrefManager.saveUser(userData);
        if (allPhoneNumber.contains(mobile)) {
            if (allPassword.contains(password1)) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        } else {
            showDialog("Invalid Mobile or Password", false);
        }
    }


    public void showDialog(String message, Boolean isFinish) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (dialog, id) -> {
            dialog.dismiss();
            if (isFinish) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}