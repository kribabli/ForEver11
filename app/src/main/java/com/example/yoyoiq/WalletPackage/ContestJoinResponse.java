package com.example.yoyoiq.WalletPackage;

import com.google.gson.annotations.SerializedName;

public class ContestJoinResponse {


  @SerializedName("status")
   boolean status;

  @SerializedName("response")
  String Response;

 public ContestJoinResponse(boolean status, String response) {
  this.status = status;
  Response = response;
 }

 public boolean isStatus() {
  return status;
 }

 public void setStatus(boolean status) {
  this.status = status;
 }

 public String getResponse() {
  return Response;
 }

 public void setResponse(String response) {
  Response = response;
 }
}
