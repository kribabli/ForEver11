package com.example.yoyoiq.UpcommingReq;

import com.google.gson.annotations.SerializedName;

public class TeambItem {

    @SerializedName("logo_url")
    private String logoUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("short_name")
    private String shortName;

    @SerializedName("team_id")
    private int teamId;

    @SerializedName("scores")
    private String scores;

    @SerializedName("overs")
    private String overs;

    @SerializedName("scores_full")
    private String scoresFull;

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public String getScores() {
        return scores;
    }

    public void setOvers(String overs) {
        this.overs = overs;
    }

    public String getOvers() {
        return overs;
    }

    public void setScoresFull(String scoresFull) {
        this.scoresFull = scoresFull;
    }

    public String getScoresFull() {
        return scoresFull;
    }
}