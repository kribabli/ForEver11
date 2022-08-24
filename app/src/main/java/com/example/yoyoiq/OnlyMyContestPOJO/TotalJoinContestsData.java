package com.example.yoyoiq.OnlyMyContestPOJO;

public class TotalJoinContestsData {
    String contest_description;
    String contest_id;
    String contest_name;
    String first_price;
    String match_id;
    String prize_pool;
    String total_team;
    String join_team;
    String price_contribution;

    public TotalJoinContestsData(String contest_description, String contest_id, String contest_name, String first_price, String match_id, String prize_pool, String total_team, String join_team, String price_contribution) {
        this.contest_description = contest_description;
        this.contest_id = contest_id;
        this.contest_name = contest_name;
        this.first_price = first_price;
        this.match_id = match_id;
        this.prize_pool = prize_pool;
        this.total_team = total_team;
        this.join_team = join_team;
        this.price_contribution = price_contribution;
    }

    public String getContest_description() {
        return contest_description;
    }

    public void setContest_description(String contest_description) {
        this.contest_description = contest_description;
    }

    public String getContest_id() {
        return contest_id;
    }

    public void setContest_id(String contest_id) {
        this.contest_id = contest_id;
    }

    public String getContest_name() {
        return contest_name;
    }

    public void setContest_name(String contest_name) {
        this.contest_name = contest_name;
    }

    public String getFirst_price() {
        return first_price;
    }

    public void setFirst_price(String first_price) {
        this.first_price = first_price;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getPrize_pool() {
        return prize_pool;
    }

    public void setPrize_pool(String prize_pool) {
        this.prize_pool = prize_pool;
    }

    public String getTotal_team() {
        return total_team;
    }

    public void setTotal_team(String total_team) {
        this.total_team = total_team;
    }

    public String getJoin_team() {
        return join_team;
    }

    public void setJoin_team(String join_team) {
        this.join_team = join_team;
    }

    public String getPrice_contribution() {
        return price_contribution;
    }

    public void setPrice_contribution(String price_contribution) {
        this.price_contribution = price_contribution;
    }
}
