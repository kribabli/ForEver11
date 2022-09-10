package com.example.yoyoiq.LoginPojo;

import com.example.yoyoiq.CreatedTeamPOJO.TeamResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginResponse {
    String status;
    String data;
    @SerializedName("response")
    private ArrayList<userLoginData> userLoginDataArrayList;

    public LoginResponse(String status, String data, ArrayList<userLoginData> userLoginDataArrayList) {
        this.status = status;
        this.data = data;
        this.userLoginDataArrayList = userLoginDataArrayList;
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

    public ArrayList<userLoginData> getUserLoginDataArrayList() {
        return userLoginDataArrayList;
    }

    public void setUserLoginDataArrayList(ArrayList<userLoginData> userLoginDataArrayList) {
        this.userLoginDataArrayList = userLoginDataArrayList;
    }
}
