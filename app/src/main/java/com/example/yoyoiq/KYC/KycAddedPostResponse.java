package com.example.yoyoiq.KYC;

import com.google.gson.annotations.SerializedName;

public class KycAddedPostResponse {
     @SerializedName("status")
     boolean status;

     @SerializedName("response")
     String response;

    public KycAddedPostResponse(boolean status, String response) {
        this.status = status;
        this.response = response;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
