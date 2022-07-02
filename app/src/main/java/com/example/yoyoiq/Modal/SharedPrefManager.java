package com.example.yoyoiq.Modal;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static String SHARED_PREF_NAME = "YoyoIqUserDetails";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public void saveUser(UserData userData) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("userName", userData.getUserName());
        editor.putString("userPassword", userData.getPassword());
        editor.putBoolean("logged", true);
        editor.apply();

    }

    public boolean isLoggedIn() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged", false);

    }

    public void logout() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


}
