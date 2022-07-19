package com.example.yoyoiq.PlayerPOJO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class players {
    int pid;
    String title;
    String short_name;
    String first_name;
    String last_name;
    String middle_name;
    String birthdate;
    String birthplace;
    String country;
    @SerializedName("primary_team")
    private ArrayList<primary_team> primary_team;
    String thumb_url;
    String logo_url;
    String playing_role;
    String batting_style;
    String bowling_style;
    String fielding_position;
    String recent_match;
    String recent_appearance;
    String fantasy_player_rating;
    String nationality;

    public players(int pid, String title, String short_name, String first_name, String last_name, String middle_name, String birthdate, String birthplace, String country, ArrayList<com.example.yoyoiq.PlayerPOJO.primary_team> primary_team, String thumb_url, String logo_url, String playing_role, String batting_style, String bowling_style, String fielding_position, String recent_match, String recent_appearance, String fantasy_player_rating, String nationality) {
        this.pid = pid;
        this.title = title;
        this.short_name = short_name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
        this.country = country;
        this.primary_team = primary_team;
        this.thumb_url = thumb_url;
        this.logo_url = logo_url;
        this.playing_role = playing_role;
        this.batting_style = batting_style;
        this.bowling_style = bowling_style;
        this.fielding_position = fielding_position;
        this.recent_match = recent_match;
        this.recent_appearance = recent_appearance;
        this.fantasy_player_rating = fantasy_player_rating;
        this.nationality = nationality;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ArrayList<com.example.yoyoiq.PlayerPOJO.primary_team> getPrimary_team() {
        return primary_team;
    }

    public void setPrimary_team(ArrayList<com.example.yoyoiq.PlayerPOJO.primary_team> primary_team) {
        this.primary_team = primary_team;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getPlaying_role() {
        return playing_role;
    }

    public void setPlaying_role(String playing_role) {
        this.playing_role = playing_role;
    }

    public String getBatting_style() {
        return batting_style;
    }

    public void setBatting_style(String batting_style) {
        this.batting_style = batting_style;
    }

    public String getBowling_style() {
        return bowling_style;
    }

    public void setBowling_style(String bowling_style) {
        this.bowling_style = bowling_style;
    }

    public String getFielding_position() {
        return fielding_position;
    }

    public void setFielding_position(String fielding_position) {
        this.fielding_position = fielding_position;
    }

    public String getRecent_match() {
        return recent_match;
    }

    public void setRecent_match(String recent_match) {
        this.recent_match = recent_match;
    }

    public String getRecent_appearance() {
        return recent_appearance;
    }

    public void setRecent_appearance(String recent_appearance) {
        this.recent_appearance = recent_appearance;
    }

    public String getFantasy_player_rating() {
        return fantasy_player_rating;
    }

    public void setFantasy_player_rating(String fantasy_player_rating) {
        this.fantasy_player_rating = fantasy_player_rating;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
