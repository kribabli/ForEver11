package com.example.yoyoiq.KYC;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewKycResponse {

  @SerializedName("status")
  boolean status;
  @SerializedName("kycdetail")
  List<KycFullDetailsResponse> kycDetails;
  @SerializedName("response")
  String response;


    public ViewKycResponse(boolean status, List<KycFullDetailsResponse> kycDetails, String response) {
        this.status = status;
        this.kycDetails = kycDetails;
        this.response = response;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<KycFullDetailsResponse> getKycDetails() {
        return kycDetails;
    }

    public void setKycDetails(List<KycFullDetailsResponse> kycDetails) {
        this.kycDetails = kycDetails;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
