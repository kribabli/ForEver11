package com.example.yoyoiq.ContestPOJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Contests {
    @SerializedName("status")
    public boolean status;

    @SerializedName("response")
    private List<ContestsList> response;

    public Contests(boolean status, List<ContestsList> response) {
        this.status = status;
        this.response = response;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ContestsList> getResponse() {
        return response;
    }

    public void setResponse(List<ContestsList> response) {
        this.response = response;
    }
}
