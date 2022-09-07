package com.example.yoyoiq.UpdatePassword;

public class UpdatePassword {
    public boolean status;
    public String response;

    public UpdatePassword(boolean status, String response) {
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
