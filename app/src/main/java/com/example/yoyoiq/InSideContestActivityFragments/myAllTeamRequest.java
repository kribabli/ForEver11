package com.example.yoyoiq.InSideContestActivityFragments;

public class myAllTeamRequest {

   String TeamName;
   String match_id, user_id, captain, vicecaptain;
   int batsman, boller, allrounder, wkeeper,teamAcount,teamBcount;

   public myAllTeamRequest(String teamName, String match_id, String user_id, String captain, String vicecaptain, int batsman, int boller, int allrounder, int wkeeper, int teamAcount, int teamBcount) {
      TeamName = teamName;
      this.match_id = match_id;
      this.user_id = user_id;
      this.captain = captain;
      this.vicecaptain = vicecaptain;
      this.batsman = batsman;
      this.boller = boller;
      this.allrounder = allrounder;
      this.wkeeper = wkeeper;
      this.teamAcount = teamAcount;
      this.teamBcount = teamBcount;
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
}
