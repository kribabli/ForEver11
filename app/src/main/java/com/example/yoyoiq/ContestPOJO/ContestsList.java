package com.example.yoyoiq.ContestPOJO;

import com.google.gson.annotations.SerializedName;

public class ContestsList {
    @SerializedName("contest_id")
    private String contest_id;

    @SerializedName("contest_name")
    private String contest_name;

    @SerializedName("contest_tag")
    private String contest_tag;

    @SerializedName("winners")
    private String winners;

    @SerializedName("prize_pool")
    private String prize_pool;

    @SerializedName("total_team")
    private String total_team;

    @SerializedName("join_team")
    private String join_team;

    @SerializedName("entry")
    private String entry;

    @SerializedName("contest_description")
    private String contest_description;

    @SerializedName("contest_note1")
    private String contest_note1;

    @SerializedName("contest_note2")
    private String contest_note2;

    @SerializedName("winning_note")
    private String winning_note;

    @SerializedName("match_id")
    private String match_id;

    @SerializedName("type")
    private String type;

    @SerializedName("userid")
    private String userid;

    @SerializedName("admin_com")
    private String admin_com;

    @SerializedName("bonus_cut_percentage")
    private String bonus_cut_percentage;

    @SerializedName("cancel_contest")
    private String cancel_contest;

    @SerializedName("on_per")
    private String on_per;

    @SerializedName("status")
    private String status;

    public ContestsList(String contest_id, String contest_name, String contest_tag, String winners, String prize_pool, String total_team, String join_team, String entry, String contest_description, String contest_note1, String contest_note2, String winning_note, String match_id, String type, String userid, String admin_com, String bonus_cut_percentage, String cancel_contest, String on_per, String status) {
        this.contest_id = contest_id;
        this.contest_name = contest_name;
        this.contest_tag = contest_tag;
        this.winners = winners;
        this.prize_pool = prize_pool;
        this.total_team = total_team;
        this.join_team = join_team;
        this.entry = entry;
        this.contest_description = contest_description;
        this.contest_note1 = contest_note1;
        this.contest_note2 = contest_note2;
        this.winning_note = winning_note;
        this.match_id = match_id;
        this.type = type;
        this.userid = userid;
        this.admin_com = admin_com;
        this.bonus_cut_percentage = bonus_cut_percentage;
        this.cancel_contest = cancel_contest;
        this.on_per = on_per;
        this.status = status;
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

    public String getContest_tag() {
        return contest_tag;
    }

    public void setContest_tag(String contest_tag) {
        this.contest_tag = contest_tag;
    }

    public String getWinners() {
        return winners;
    }

    public void setWinners(String winners) {
        this.winners = winners;
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

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getContest_description() {
        return contest_description;
    }

    public void setContest_description(String contest_description) {
        this.contest_description = contest_description;
    }

    public String getContest_note1() {
        return contest_note1;
    }

    public void setContest_note1(String contest_note1) {
        this.contest_note1 = contest_note1;
    }

    public String getContest_note2() {
        return contest_note2;
    }

    public void setContest_note2(String contest_note2) {
        this.contest_note2 = contest_note2;
    }

    public String getWinning_note() {
        return winning_note;
    }

    public void setWinning_note(String winning_note) {
        this.winning_note = winning_note;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAdmin_com() {
        return admin_com;
    }

    public void setAdmin_com(String admin_com) {
        this.admin_com = admin_com;
    }

    public String getBonus_cut_percentage() {
        return bonus_cut_percentage;
    }

    public void setBonus_cut_percentage(String bonus_cut_percentage) {
        this.bonus_cut_percentage = bonus_cut_percentage;
    }

    public String getCancel_contest() {
        return cancel_contest;
    }

    public void setCancel_contest(String cancel_contest) {
        this.cancel_contest = cancel_contest;
    }

    public String getOn_per() {
        return on_per;
    }

    public void setOn_per(String on_per) {
        this.on_per = on_per;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}