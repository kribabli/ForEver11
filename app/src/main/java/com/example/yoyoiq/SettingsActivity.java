package com.example.yoyoiq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.common.HelperData;
import com.example.yoyoiq.common.SessionManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class SettingsActivity extends AppCompatActivity {
    TextView backPress, userName, email, mobileNo, Country, logOut;
    SessionManager sessionManager;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initMethod();
        setAction();
        upDateProfile();
    }

    private void initMethod() {
        backPress = findViewById(R.id.backPress);
        userName = findViewById(R.id.userName);
        email = findViewById(R.id.email);
        mobileNo = findViewById(R.id.mobileNo);
        Country = findViewById(R.id.Country);
        logOut = findViewById(R.id.logOut);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        sessionManager = new SessionManager(getApplicationContext());
    }

    private void setAction() {
        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogout();
            }
        });
    }

    private void upDateProfile() {
        userName.setText(HelperData.UserName);
        email.setText(HelperData.UserEmail);
        mobileNo.setText(HelperData.Usermobile);
        Country.setText("India");
    }

    private void userLogout() {
        gsc.signOut();
        sessionManager.logout();
        HelperData.UserId = "";
        HelperData.UserName = "";
        HelperData.Usermobile = "";
        HelperData.UserEmail = "";
        Intent intent = new Intent(SettingsActivity.this, FrontActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show();
    }
}