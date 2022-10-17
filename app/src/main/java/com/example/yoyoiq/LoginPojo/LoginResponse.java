package com.example.yoyoiq.LoginPojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginResponse {


    public boolean status;
    public String data;
    @SerializedName("response")
    private ArrayList<userLoginData> userLoginDataArrayList;

    public LoginResponse(boolean status, String data, ArrayList<userLoginData> userLoginDataArrayList) {
        this.status = status;
        this.data = data;
        this.userLoginDataArrayList = userLoginDataArrayList;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<userLoginData> getUserLoginDataArrayList() {
        return userLoginDataArrayList;
    }

    public void setUserLoginDataArrayList(ArrayList<userLoginData> userLoginDataArrayList) {
        this.userLoginDataArrayList = userLoginDataArrayList;
    }
}
