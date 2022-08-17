package com.example.yoyoiq.WalletPackage;

 public class BalanceDetails {
     String balance;
     String add_bonus;

     public BalanceDetails(String balance, String add_bonus) {
         this.balance = balance;
         this.add_bonus = add_bonus;
     }

     public String getBalance() {
         return balance;
     }

     public void setBalance(String balance) {
         this.balance = balance;
     }

     public String getAdd_bonus() {
         return add_bonus;
     }

     public void setAdd_bonus(String add_bonus) {
         this.add_bonus = add_bonus;
     }
 }
