package com.example.yoyoiq.BannerPOJO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Banner {

    @SerializedName("status")
    public boolean status;

    @SerializedName("response")
    private ArrayList<BannerResponse> response;

    public Banner(boolean status, ArrayList<BannerResponse> response) {
        this.status = status;
        this.response = response;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<BannerResponse> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<BannerResponse> response) {
        this.response = response;
    }
}
