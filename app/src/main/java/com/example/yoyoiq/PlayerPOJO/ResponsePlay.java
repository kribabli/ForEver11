package com.example.yoyoiq.PlayerPOJO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponsePlay {

    @SerializedName("teama")
    private TeamA teama;

    @SerializedName("teamb")
    private TeamB teamb;

    @SerializedName("teams")
    private ArrayList<teams> teams;

    @SerializedName("players")
    private ArrayList<players> players;

    public ResponsePlay(TeamA teama, TeamB teamb, ArrayList<teams> teams, ArrayList<players> players) {
        this.teama = teama;
        this.teamb = teamb;
        this.teams = teams;
        this.players = players;
    }

    public TeamA getTeama() {
        return teama;
    }

    public void setTeama(TeamA teama) {
        this.teama = teama;
    }

    public TeamB getTeamb() {
        return teamb;
    }

    public void setTeamb(TeamB teamb) {
        this.teamb = teamb;
    }

    public ArrayList<teams> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<teams> teams) {
        this.teams = teams;
    }

    public ArrayList<players> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<players> players) {
        this.players = players;
    }
}
