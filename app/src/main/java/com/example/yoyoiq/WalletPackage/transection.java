package com.example.yoyoiq.WalletPackage;

import com.google.gson.annotations.SerializedName;

public class transection {

  @SerializedName("user_id")
   String user_id;
  @SerializedName("type")
   String type;

  @SerializedName("amount")
  String amount;
  @SerializedName("transection_id")
  String transection_id;

  @SerializedName("created_date")
  String created_date;

 public transection(String user_id, String type, String amount, String transection_id, String created_date) {
  this.user_id = user_id;
  this.type = type;
  this.amount = amount;
  this.transection_id = transection_id;
  this.created_date = created_date;
 }

 public String getUser_id() {
  return user_id;
 }

 public void setUser_id(String user_id) {
  this.user_id = user_id;
 }

 public String getType() {
  return type;
 }

 public void setType(String type) {
  this.type = type;
 }

 public String getAmount() {
  return amount;
 }

 public void setAmount(String amount) {
  this.amount = amount;
 }

 public String getTransection_id() {
  return transection_id;
 }

 public void setTransection_id(String transection_id) {
  this.transection_id = transection_id;
 }

 public String getCreated_date() {
  return created_date;
 }

 public void setCreated_date(String created_date) {
  this.created_date = created_date;
 }
}
