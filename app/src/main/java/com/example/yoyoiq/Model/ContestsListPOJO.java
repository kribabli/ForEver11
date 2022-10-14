package com.example.yoyoiq.Model;

public class ContestsListPOJO {
    String contest_id;
    String contest_name;
    String entry;
    String join_team;
    String prize_pool;
    String total_team;
    String winners;
    String winning_percentage;
    String upto;
    String contest_description;
    String matchA;
    String matchB;
    String match_id;
    String price_contribution;

    public ContestsListPOJO(String contest_id, String contest_name, String entry, String join_team, String prize_pool, String total_team, String winners, String winning_percentage, String upto, String contest_description, String matchA, String matchB, String match_id, String price_contribution) {
        this.contest_id = contest_id;
        this.contest_name = contest_name;
        this.entry = entry;
        this.join_team = join_team;
        this.prize_pool = prize_pool;
        this.total_team = total_team;
        this.winners = winners;
        this.winning_percentage = winning_percentage;
        this.upto = upto;
        this.contest_description = contest_description;
        this.matchA = matchA;
        this.matchB = matchB;
        this.match_id = match_id;
        this.price_contribution = price_contribution;
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

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getJoin_team() {
        return join_team;
    }

    public void setJoin_team(String join_team) {
        this.join_team = join_team;
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

    public String getWinners() {
        return winners;
    }

    public void setWinners(String winners) {
        this.winners = winners;
    }

    public String getWinning_percentage() {
        return winning_percentage;
    }

    public void setWinning_percentage(String winning_percentage) {
        this.winning_percentage = winning_percentage;
    }

    public String getUpto() {
        return upto;
    }

    public void setUpto(String upto) {
        this.upto = upto;
    }

    public String getContest_description() {
        return contest_description;
    }

    public void setContest_description(String contest_description) {
        this.contest_description = contest_description;
    }

    public String getMatchA() {
        return matchA;
    }

    public void setMatchA(String matchA) {
        this.matchA = matchA;
    }

    public String getMatchB() {
        return matchB;
    }

    public void setMatchB(String matchB) {
        this.matchB = matchB;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getPrice_contribution() {
        return price_contribution;
    }

    public void setPrice_contribution(String price_contribution) {
        this.price_contribution = price_contribution;
    }
}