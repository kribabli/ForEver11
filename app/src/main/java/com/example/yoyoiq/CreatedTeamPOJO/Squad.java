package com.example.yoyoiq.CreatedTeamPOJO;

public class Squad {
    public boolean added;
    public String country;
    public double fantasy_player_rating;
    public boolean isCap;
    public boolean isVcap;
    public String matchId;
    public int pid;
    public String playing_role;
    public String points;
    public String title;

    public Squad(boolean added, String country, double fantasy_player_rating, boolean isCap, boolean isVcap, String matchId, int pid, String playing_role, String points, String title) {
        this.added = added;
        this.country = country;
        this.fantasy_player_rating = fantasy_player_rating;
        this.isCap = isCap;
        this.isVcap = isVcap;
        this.matchId = matchId;
        this.pid = pid;
        this.playing_role = playing_role;
        this.points = points;
        this.title = title;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getFantasy_player_rating() {
        return fantasy_player_rating;
    }

    public void setFantasy_player_rating(double fantasy_player_rating) {
        this.fantasy_player_rating = fantasy_player_rating;
    }

    public boolean isCap() {
        return isCap;
    }

    public void setCap(boolean cap) {
        isCap = cap;
    }

    public boolean isVcap() {
        return isVcap;
    }

    public void setVcap(boolean vcap) {
        isVcap = vcap;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPlaying_role() {
        return playing_role;
    }

    public void setPlaying_role(String playing_role) {
        this.playing_role = playing_role;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
