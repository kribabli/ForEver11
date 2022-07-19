package com.example.yoyoiq.PlayerPOJO;

import com.google.gson.annotations.SerializedName;

public class ResponsePlayer {
    @SerializedName("status")
    private String status;

    @SerializedName("response")
    private ResponsePlay responsePlay;

    public ResponsePlayer(String status, ResponsePlay responsePlay) {
        this.status = status;
        this.responsePlay = responsePlay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResponsePlay getResponsePlay() {
        return responsePlay;
    }

    public void setResponsePlay(ResponsePlay responsePlay) {
        this.responsePlay = responsePlay;
    }
}
