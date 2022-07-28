package com.example.yoyoiq.UpcommingReq;

import com.google.gson.annotations.SerializedName;

public class TossItem {

    @SerializedName("winner")
    private int winner;

    @SerializedName("decision")
    private int decision;

    @SerializedName("text")
    private String text;

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int getWinner() {
        return winner;
    }

    public void setDecision(int decision) {
        this.decision = decision;
    }

    public int getDecision() {
        return decision;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}