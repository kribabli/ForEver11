package com.example.yoyoiq.KYC;

import com.google.gson.annotations.SerializedName;

public class KycFullDetailsResponse {
    @SerializedName("user_id")
    String user_id;

    @SerializedName("fullname")
    String fullname;

    @SerializedName("dob")
    String dob;

    @SerializedName("address")
    String address;

    @SerializedName("account_no")
    String account_no;

    @SerializedName("ifsc_code")
    String ifsc_code;

    @SerializedName("bank_name")
    String bank_name;

    @SerializedName("adhar_no")
    String adhar_no;

    @SerializedName("pancard_no")
    String pancard_no;

    @SerializedName("pancard")
    String pancard;

    @SerializedName("status")
    String status;

    public KycFullDetailsResponse(String user_id, String fullname, String dob, String address, String account_no, String ifsc_code, String bank_name, String adhar_no, String pancard_no, String pancard, String status) {
        this.user_id = user_id;
        this.fullname = fullname;
        this.dob = dob;
        this.address = address;
        this.account_no = account_no;
        this.ifsc_code = ifsc_code;
        this.bank_name = bank_name;
        this.adhar_no = adhar_no;
        this.pancard_no = pancard_no;
        this.pancard = pancard;
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getAdhar_no() {
        return adhar_no;
    }

    public void setAdhar_no(String adhar_no) {
        this.adhar_no = adhar_no;
    }

    public String getPancard_no() {
        return pancard_no;
    }

    public void setPancard_no(String pancard_no) {
        this.pancard_no = pancard_no;
    }

    public String getPancard() {
        return pancard;
    }

    public void setPancard(String pancard) {
        this.pancard = pancard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
