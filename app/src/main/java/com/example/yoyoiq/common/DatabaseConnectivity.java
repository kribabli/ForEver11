package com.example.yoyoiq.common;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DatabaseConnectivity {
    public DatabaseReference getDatabasePath(Context context) {
        DatabaseReference databaseReferencePath = FirebaseDatabase.getInstance().getReference();
        return databaseReferencePath;
    }

    public StorageReference getDatabaseStorage(Context context) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference().child("YoyoIq/");
        return storageReference;
    }

    public boolean network(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected() && internetIsConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }

    private SharedPreferences getSp(Context context) {
        SharedPreferences sp = context.getSharedPreferences("path", MODE_PRIVATE);
        return sp;
    }

}
