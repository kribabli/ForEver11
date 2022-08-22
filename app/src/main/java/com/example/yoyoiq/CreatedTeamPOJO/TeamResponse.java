package com.example.yoyoiq.CreatedTeamPOJO;

import com.example.yoyoiq.InSideContestActivityFragments.ShortTeamResponse;
import com.example.yoyoiq.InSideContestActivityFragments.myAllTeamRequest;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TeamResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("match_id")
    private String match_id;

    @SerializedName("squads")
    private ArrayList<Squad> squads;

    @SerializedName("short_squads")
    private ArrayList<ShortTeamResponse> short_squads;

    public TeamResponse(String id, String user_id, String match_id, ArrayList<Squad> squads, ArrayList<ShortTeamResponse> short_squads) {
        this.id = id;
        this.user_id = user_id;
        this.match_id = match_id;
        this.squads = squads;
        this.short_squads = short_squads;
    }

    public ArrayList<ShortTeamResponse> getShort_squads() {
        return short_squads;
    }

    public void setShort_squads(ArrayList<ShortTeamResponse> short_squads) {
        this.short_squads = short_squads;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public ArrayList<Squad> getSquads() {
        return squads;
    }

    public void setSquads(ArrayList<Squad> squads) {
        this.squads = squads;
    }
}
