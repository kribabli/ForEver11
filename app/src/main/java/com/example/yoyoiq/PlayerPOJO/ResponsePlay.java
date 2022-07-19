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

}
