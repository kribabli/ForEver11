package com.example.yoyoiq.CreatedTeamPOJO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CreatedTeamResponse {
    @SerializedName("status")
    private boolean status;

    @SerializedName("response")
    private ArrayList<TeamResponse> response;

    public CreatedTeamResponse(boolean status, ArrayList<TeamResponse> response) {
        this.status = status;
        this.response = response;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<TeamResponse> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<TeamResponse> response) {
        this.response = response;
    }
}
