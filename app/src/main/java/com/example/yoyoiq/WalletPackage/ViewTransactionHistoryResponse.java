package com.example.yoyoiq.WalletPackage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewTransactionHistoryResponse {

    @SerializedName("status")
    boolean status;
    @SerializedName("transection")
    List<transection> detail;

    @SerializedName("response")
    String response;

    public ViewTransactionHistoryResponse(boolean status, List<transection> detail, String response) {
        this.status = status;
        this.detail = detail;
        this.response = response;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<transection> getDetail() {
        return detail;
    }

    public void setDetail(List<transection> detail) {
        this.detail = detail;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
