package com.example.yoyoiq.JoinContest;

 public class JoinContestsResponse {
  String status;
  String response;

  public JoinContestsResponse(String status, String response) {
   this.status = status;
   this.response = response;
  }

  public String getStatus() {
   return status;
  }

  public void setStatus(String status) {
   this.status = status;
  }

  public String getResponse() {
   return response;
  }

  public void setResponse(String response) {
   this.response = response;
  }
 }
