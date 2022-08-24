package com.example.yoyoiq.InSideContestActivityFragments;

 public class AllSelectedPlayerFromServer {
  int pid;
  String matchId, title, country, playing_role;
  double fantasy_player_rating;
  boolean added, isCap, isVcap;
  String points;

  public AllSelectedPlayerFromServer(int pid, String matchId, String title, String country, String playing_role, double fantasy_player_rating, boolean added, boolean isCap, boolean isVcap, String points) {
   this.pid = pid;
   this.matchId = matchId;
   this.title = title;
   this.country = country;
   this.playing_role = playing_role;
   this.fantasy_player_rating = fantasy_player_rating;
   this.added = added;
   this.isCap = isCap;
   this.isVcap = isVcap;
   this.points = points;
  }

  public int getPid() {
   return pid;
  }

  public void setPid(int pid) {
   this.pid = pid;
  }

  public String getMatchId() {
   return matchId;
  }

  public void setMatchId(String matchId) {
   this.matchId = matchId;
  }

  public String getTitle() {
   return title;
  }

  public void setTitle(String title) {
   this.title = title;
  }

  public String getCountry() {
   return country;
  }

  public void setCountry(String country) {
   this.country = country;
  }

  public String getPlaying_role() {
   return playing_role;
  }

  public void setPlaying_role(String playing_role) {
   this.playing_role = playing_role;
  }

  public double getFantasy_player_rating() {
   return fantasy_player_rating;
  }

  public void setFantasy_player_rating(double fantasy_player_rating) {
   this.fantasy_player_rating = fantasy_player_rating;
  }

  public boolean isAdded() {
   return added;
  }

  public void setAdded(boolean added) {
   this.added = added;
  }

  public boolean isCap() {
   return isCap;
  }

  public void setCap(boolean cap) {
   isCap = cap;
  }

  public boolean isVcap() {
   return isVcap;
  }

  public void setVcap(boolean vcap) {
   isVcap = vcap;
  }

  public String getPoints() {
   return points;
  }

  public void setPoints(String points) {
   this.points = points;
  }
 }
