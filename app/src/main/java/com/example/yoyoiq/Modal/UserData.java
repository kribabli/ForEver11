package com.example.yoyoiq.Modal;

public class UserData {
    String userName;
    String mobileNo;
    String emailId;
    String password;

    public UserData(String userName, String mobileNo, String emailId, String password) {
        this.userName = userName;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
