package com.example.yoyoiq.Model;

public class UserData {
    String userName;
    String mobileNo;
    String emailId;
    String user_id;

    public UserData(String userName, String mobileNo, String emailId, String user_id) {
        this.userName = userName;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.user_id = user_id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
