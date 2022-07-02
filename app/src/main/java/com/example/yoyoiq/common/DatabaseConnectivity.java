package com.example.yoyoiq.common;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseConnectivity {
    ProgressDialog dialog;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    String TIME_SERVER = "ntp.xs4all.nl";
    public static final int LOCATION_REQUEST = 500;
    public String[] monthArray = {"Select Month", "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

    private static DatabaseConnectivity single_instance = null;

    // All Common Uses Functionality are defined Globally like Progress dialog, firebase realtime Path,Storage Path, Alert Dialog,Shared Preferences;


    public static DatabaseConnectivity getInstance() {
        if (single_instance == null)
            single_instance = new DatabaseConnectivity();
        return single_instance;
    }
    // Realtime Firebase Connectivity
    public DatabaseReference getDatabasePath(Context context) {
        DatabaseReference databaseReferencePath = FirebaseDatabase.getInstance().getReference();
        return databaseReferencePath;
    }

//  FireBase Storage Path
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

    @SuppressLint("SimpleDateFormat")
    public String year() {
        return new SimpleDateFormat("yyyy").format(new Date());
    }

    @SuppressLint("SimpleDateFormat")
    public String monthName() {
        return new SimpleDateFormat("MMMM").format(new Date());
    }

    @SuppressLint("SimpleDateFormat")
    public String monthInNumber() {
        return new SimpleDateFormat("MM").format(new Date());
    }

    @SuppressLint("SimpleDateFormat")
    public String date() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public boolean locationPermission(Activity context) {
        boolean st = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                context.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
                st = false;
            } else {
                st = true;
            }
        }
        return st;
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

    public void showAlertDialog(String title, String message, boolean check, Context context) {
        closeDialog((Activity) context);
        if (alertDialog != null) {
            try {
                alertDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        builder = new AlertDialog.Builder(context);
        builder.setCancelable(check);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
        alertDialog = builder.create();
        alertDialog.cancel();
        alertDialog.show();
    }

    public void setProgressDialog(String title, String message, Context context, Activity activity) {
        closeDialog(activity);
        dialog = new ProgressDialog(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.create();
        dialog.setCancelable(false);
        dialog.setProgressStyle(dialog.STYLE_SPINNER);
        if (!dialog.isShowing() && !activity.isFinishing()) {
            try {
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void closeDialog(Activity activity) {
        if (dialog != null) {
            if (dialog.isShowing() && !activity.isFinishing()) {
                try {
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
