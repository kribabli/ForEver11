package com.example.yoyoiq.PlayerPOJO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TeamB {
    @SerializedName("team_id")
    private String team_id;

    @SerializedName("squads")
    private ArrayList<squadsB> squads;

    public TeamB(String team_id, ArrayList<squadsB> squads) {
        this.team_id = team_id;
        this.squads = squads;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public ArrayList<squadsB> getSquads() {
        return squads;
    }

    public void setSquads(ArrayList<squadsB> squads) {
        this.squads = squads;
    }
}
