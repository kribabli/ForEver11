package com.example.yoyoiq.LoginPojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginResponse {
    String status;
    String data;
    @SerializedName("reponse")
    private ArrayList<userLoginData>loginData ;

    public LoginResponse(String status, String data, ArrayList<userLoginData> loginData) {
        this.status = status;
        this.data = data;
        this.loginData = loginData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<userLoginData> getLoginData() {
        return loginData;
    }

    public void setLoginData(ArrayList<userLoginData> loginData) {
        this.loginData = loginData;
    }
}
