package com.example.yoyoiq.UpComingMatchPOJO;

import com.google.gson.annotations.SerializedName;

public class Status {
    @SerializedName("status")
    private String status;

    @SerializedName("response")
    private ResponseClass responseClass;

    public Status(String status, ResponseClass responseClass) {
        this.status = status;
        this.responseClass = responseClass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResponseClass getResponseClass() {
        return responseClass;
    }

    public void setResponseClass(ResponseClass responseClass) {
        this.responseClass = responseClass;
    }
}
