package com.example.SendMyTeamOnServerPojo;

import com.example.yoyoiq.Model.AllSelectedPlayer;

import java.util.List;

public class SendCreatedTeamServer {
     String UserId;
     String Match_id;

     private List<AllSelectedPlayer> squads;

    public SendCreatedTeamServer(String userId, String match_id, List<AllSelectedPlayer> squads) {
        UserId = userId;
        Match_id = match_id;
        this.squads = squads;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getMatch_id() {
        return Match_id;
    }

    public void setMatch_id(String match_id) {
        Match_id = match_id;
    }

    public List<AllSelectedPlayer> getSquads() {
        return squads;
    }

    public void setSquads(List<AllSelectedPlayer> squads) {
        this.squads = squads;
    }
}
