package com.example.yoyoiq.PlayerPOJO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TeamA {

    @SerializedName("team_id")
    private String team_id;

    @SerializedName("squads")
    private ArrayList<squadsA> squads;

    public TeamA(String team_id, ArrayList<squadsA> squads) {
        this.team_id = team_id;
        this.squads = squads;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public ArrayList<squadsA> getSquads() {
        return squads;
    }

    public void setSquads(ArrayList<squadsA> squads) {
        this.squads = squads;
    }
}
