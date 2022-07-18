package com.example.yoyoiq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.yoyoiq.Modal.SharedPrefManager;
import com.example.yoyoiq.common.DatabaseConnectivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());

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


        DatabaseConnectivity databaseConnectivity=new DatabaseConnectivity();
        databaseConnectivity.getDatabasePath(this).child("VersionNameYoYoIq").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        setContentView(R.layout.activity_splash);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPrefManager.isLoggedIn()) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, FrontActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 4000);
    }

}