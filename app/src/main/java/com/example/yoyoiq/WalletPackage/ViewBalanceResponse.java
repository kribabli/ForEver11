package com.example.yoyoiq.WalletPackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ViewBalanceResponse {
 String status;
  @SerializedName("balance")
  List<BalanceDetails> balance;
  String response;

 public ViewBalanceResponse(String status, List<BalanceDetails> balance, String response) {
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

 public List<BalanceDetails> getBalance() {
  return balance;
 }

 public void setBalance(List<BalanceDetails> balance) {
  this.balance = balance;
 }

 public String getResponse() {
  return response;
 }

 public void setResponse(String response) {
  this.response = response;
 }
}



