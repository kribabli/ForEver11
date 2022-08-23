package com.example.yoyoiq.OnlyMyContestPOJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyContest1 {
    @SerializedName("status")
    private boolean status;

    @SerializedName("response")
    private List<ResponseMyContest> response;

    public MyContest1(boolean status, List<ResponseMyContest> response) {
        this.status = status;
        this.response = response;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ResponseMyContest> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseMyContest> response) {
        this.response = response;
    }
}
