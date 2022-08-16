package com.example.yoyoiq;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.common.DatabaseConnectivity;
import com.example.yoyoiq.common.HelperData;
import com.example.yoyoiq.common.SessionManager;
import com.example.yoyoiq.common.SharedPrefManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    SharedPrefManager sharedPrefManager;
    DatabaseConnectivity databaseConnectivity = new DatabaseConnectivity();
    DatabaseReference databaseReference;
    SessionManager sessionManager;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        databaseReference = FirebaseDatabase.getInstance().getReference();
        sessionManager = new SessionManager(getApplicationContext());

        //get version and other info..
        PackageManager manager = this.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(this.getPackageName(), PackageManager.GET_ACTIVITIES);
            Log.d("TAG", "ShowInfo: "
                    + "PackageName = " + info.packageName
                    + "\nVersionCode = " + info.versionCode
                    + "\nVersionName = " + info.versionName
                    + "\nPermissions = " + info.permissions);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        databaseConnectivity.getDatabasePath(this).child("VersionNameYoYoIq").setValue(info.versionName).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

        setContentView(R.layout.activity_splash);
        handler = new Handler();
        handler.postDelayed(() -> {
            if (sessionManager.isLoggedIn() || googleSignInAccount != null) {
                HelperData.UserId = sessionManager.getUserData().getUser_id();
                HelperData.UserName = sessionManager.getUserData().getUserName();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(SplashActivity.this, FrontActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}