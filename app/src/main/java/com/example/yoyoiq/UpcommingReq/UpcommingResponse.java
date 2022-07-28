package com.example.yoyoiq.UpcommingReq;

import com.google.gson.annotations.SerializedName;

public class UpcommingResponse {

    @SerializedName("response")
    private Response response;

    @SerializedName("status")
    private boolean status;

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }
}