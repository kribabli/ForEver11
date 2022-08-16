package com.example.yoyoiq.OnlyTeamPreView;

public class OnlyTeamPreviewModel {
    String TeamNameA, TeamNameB;
    String match_id, user_id, captain, vicecaptain;
    int batsman, boller, allrounder, wkeeper;
    String CaptainName, VCName, wkName, BATName, ARName, BOWLName;

    public OnlyTeamPreviewModel(String teamNameA, String teamNameB, String match_id, String user_id, String captain, String vicecaptain, int batsman, int boller, int allrounder, int wkeeper, String captainName, String VCName, String wkName, String BATName, String ARName, String BOWLName) {
        TeamNameA = teamNameA;
        TeamNameB = teamNameB;
        this.match_id = match_id;
        this.user_id = user_id;
        this.captain = captain;
        this.vicecaptain = vicecaptain;
        this.batsman = batsman;
        this.boller = boller;
        this.allrounder = allrounder;
        this.wkeeper = wkeeper;
        CaptainName = captainName;
        this.VCName = VCName;
        this.wkName = wkName;
        this.BATName = BATName;
        this.ARName = ARName;
        this.BOWLName = BOWLName;
    }

    public String getTeamNameA() {
        return TeamNameA;
    }

    public void setTeamNameA(String teamNameA) {
        TeamNameA = teamNameA;
    }

    public String getTeamNameB() {
        return TeamNameB;
    }

    public void setTeamNameB(String teamNameB) {
        TeamNameB = teamNameB;
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

    public String getCaptainName() {
        return CaptainName;
    }

    public void setCaptainName(String captainName) {
        CaptainName = captainName;
    }

    public String getVCName() {
        return VCName;
    }

    public void setVCName(String VCName) {
        this.VCName = VCName;
    }

    public String getWkName() {
        return wkName;
    }

    public void setWkName(String wkName) {
        this.wkName = wkName;
    }

    public String getBATName() {
        return BATName;
    }

    public void setBATName(String BATName) {
        this.BATName = BATName;
    }

    public String getARName() {
        return ARName;
    }

    public void setARName(String ARName) {
        this.ARName = ARName;
    }

    public String getBOWLName() {
        return BOWLName;
    }

    public void setBOWLName(String BOWLName) {
        this.BOWLName = BOWLName;
    }
}
