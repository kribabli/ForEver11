package com.example.yoyoiq.UpcommingReq;

import com.google.gson.annotations.SerializedName;

public class CompetitionItem {

    @SerializedName("country")
    private String country;

    @SerializedName("match_format")
    private String matchFormat;

    @SerializedName("total_teams")
    private String totalTeams;

    @SerializedName("total_rounds")
    private String totalRounds;

    @SerializedName("dateend")
    private String dateend;

    @SerializedName("title")
    private String title;

    @SerializedName("type")
    private String type;

    @SerializedName("datestart")
    private String datestart;

    @SerializedName("season")
    private String season;

    @SerializedName("abbr")
    private String abbr;

    @SerializedName("category")
    private String category;

    @SerializedName("cid")
    private int cid;

    @SerializedName("status")
    private String status;

    @SerializedName("total_matches")
    private String totalMatches;

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setMatchFormat(String matchFormat) {
        this.matchFormat = matchFormat;
    }

    public String getMatchFormat() {
        return matchFormat;
    }

    public void setTotalTeams(String totalTeams) {
        this.totalTeams = totalTeams;
    }

    public String getTotalTeams() {
        return totalTeams;
    }

    public void setTotalRounds(String totalRounds) {
        this.totalRounds = totalRounds;
    }

    public String getTotalRounds() {
        return totalRounds;
    }

    public void setDateend(String dateend) {
        this.dateend = dateend;
    }

    public String getDateend() {
        return dateend;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setDatestart(String datestart) {
        this.datestart = datestart;
    }

    public String getDatestart() {
        return datestart;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSeason() {
        return season;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCid() {
        return cid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setTotalMatches(String totalMatches) {
        this.totalMatches = totalMatches;
    }

    public String getTotalMatches() {
        return totalMatches;
    }
}