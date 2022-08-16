package com.example.yoyoiq.WalletPackage;

 public class ViewBalanceResponse {

  String status;
  String balance;
  String response;

  public ViewBalanceResponse(String status, String balance, String response) {
   this.status = status;
   this.balance = balance;
   this.response = response;
  }

  public String getStatus() {
   return status;
  }

  public void setStatus(String status) {
   this.status = status;
  }

  public String getBalance() {
   return balance;
  }

  public void setBalance(String balance) {
   this.balance = balance;
  }

  public String getResponse() {
   return response;
  }

  public void setResponse(String response) {
   this.response = response;
  }
 }
