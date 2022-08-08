package com.example.yoyoiq.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.yoyoiq.LoginPojo.userLoginData;
import com.example.yoyoiq.Model.UserData;

public class SessionManager {
 private static String SHARED_PREF_NAME = "YoyoIq_UserDetails_from_Server";
 private SharedPreferences sharedPreferences;
 Context context;
 private SharedPreferences.Editor editor;

 public SessionManager(Context context) {
  this.context = context;
 }

 public void saveUser(userLoginData userData) {
  sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
  editor = sharedPreferences.edit();
  editor.putString("userId", userData.getUser_id());
  editor.putString("mobileNoServer", userData.getMobile_no());
  editor.putString("emailIdServer", userData.getEmail_id());
  editor.putString("userNameServer", userData.getUsername());
  editor.putBoolean("logged", true);
  editor.apply();
 }

 public userLoginData getUserData() {
  sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
  return new userLoginData(sharedPreferences.getString("userNameServer", ""), sharedPreferences.getString("mobileNoServer", ""),
          sharedPreferences.getString("emailIdServer", ""), sharedPreferences.getString("userId", ""));
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
