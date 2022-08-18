package com.example.yoyoiq.KYC;

import com.google.gson.annotations.SerializedName;

public class KycAddedPostResponse {
     @SerializedName("status")
     String status;

     @SerializedName("response")
     String response;

    public KycAddedPostResponse(String status, String response) {
        this.status = status;
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
