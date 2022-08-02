package com.example.yoyoiq.Model;

public class PriceContributionPOJO {
    int i;
    String position;

    public PriceContributionPOJO(int i, String position) {
        this.i = i;
        this.position = position;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
