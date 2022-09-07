package com.example.yoyoiq.InSideContestActivityFragments;

import org.json.JSONArray;

public class myAllTeamRequest {
    String TeamName;
    String TeamId;
    String match_id, user_id, captain, vicecaptain, teamAName, teamBName;
    int batsman, boller, allrounder, wkeeper, teamAcount, teamBcount;
    boolean isSlected;
    JSONArray squads;
    String logo_url_a;
    String logo_url_b;
    String date_start;
    String date_end;

    public myAllTeamRequest(String teamName, String teamId, String match_id, String user_id, String captain, String vicecaptain, String teamAName, String teamBName, int batsman, int boller, int allrounder, int wkeeper, int teamAcount, int teamBcount, boolean isSlected, JSONArray squads, String logo_url_a, String logo_url_b, String date_start, String date_end) {
      this.TeamName = teamName;
      this.TeamId = teamId;
        this.match_id = match_id;
        this.user_id = user_id;
        this.captain = captain;
        this.vicecaptain = vicecaptain;
        this.teamAName = teamAName;
        this.teamBName = teamBName;
        this.batsman = batsman;
        this.boller = boller;
        this.allrounder = allrounder;
        this.wkeeper = wkeeper;
        this.teamAcount = teamAcount;
        this.teamBcount = teamBcount;
        this.isSlected = isSlected;
        this.squads = squads;
        this.logo_url_a = logo_url_a;
        this.logo_url_b = logo_url_b;
        this.date_start = date_start;
        this.date_end = date_end;
    }

    public JSONArray getSquads() {
        return squads;
    }

    public void setSquads(JSONArray squads) {
        this.squads = squads;
    }

    public String getTeamId() {
        return TeamId;
    }

    public void setTeamId(String teamId) {
        TeamId = teamId;
    }

    public boolean isSlected() {
        return isSlected;
    }

    public void setSlected(boolean slected) {
        isSlected = slected;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getVicecaptain() {
        return vicecaptain;
    }

    public void setVicecaptain(String vicecaptain) {
        this.vicecaptain = vicecaptain;
    }

    public String getTeamAName() {
        return teamAName;
    }

    public void setTeamAName(String teamAName) {
        this.teamAName = teamAName;
    }

    public String getTeamBName() {
        return teamBName;
    }

    public void setTeamBName(String teamBName) {
        this.teamBName = teamBName;
    }

    public int getBatsman() {
        return batsman;
    }

    public void setBatsman(int batsman) {
        this.batsman = batsman;
    }

    public int getBoller() {
        return boller;
    }

    public void setBoller(int boller) {
        this.boller = boller;
    }

    public int getAllrounder() {
        return allrounder;
    }

    public void setAllrounder(int allrounder) {
        this.allrounder = allrounder;
    }

    public int getWkeeper() {
        return wkeeper;
    }

    public void setWkeeper(int wkeeper) {
        this.wkeeper = wkeeper;
    }

    public int getTeamAcount() {
        return teamAcount;
    }

    public void setTeamAcount(int teamAcount) {
        this.teamAcount = teamAcount;
    }

    public int getTeamBcount() {
        return teamBcount;
    }

    public void setTeamBcount(int teamBcount) {
        this.teamBcount = teamBcount;
    }

    public String getLogo_url_a() {
        return logo_url_a;
    }

    public void setLogo_url_a(String logo_url_a) {
        this.logo_url_a = logo_url_a;
    }

    public String getLogo_url_b() {
        return logo_url_b;
    }

    public void setLogo_url_b(String logo_url_b) {
        this.logo_url_b = logo_url_b;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }
}