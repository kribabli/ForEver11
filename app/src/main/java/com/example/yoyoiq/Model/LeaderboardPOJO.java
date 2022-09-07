package com.example.yoyoiq.Model;

public class LeaderboardPOJO {
    String id;
    String user_id;
    String team_id;
    String match_id;
    String contest_id;
    String date_time;
    String name;
    String mobile;
    int rank;
    int total_points;

    public LeaderboardPOJO(String id, String user_id, String team_id, String match_id, String contest_id, String date_time, String name, String mobile, int rank, int total_points) {
        this.id = id;
        this.user_id = user_id;
        this.team_id = team_id;
        this.match_id = match_id;
        this.contest_id = contest_id;
        this.date_time = date_time;
        this.name = name;
        this.mobile = mobile;
        this.rank = rank;
        this.total_points = total_points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getContest_id() {
        return contest_id;
    }

    public void setContest_id(String contest_id) {
        this.contest_id = contest_id;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getTotal_points() {
        return total_points;
    }

    public void setTotal_points(int total_points) {
        this.total_points = total_points;
    }
}
