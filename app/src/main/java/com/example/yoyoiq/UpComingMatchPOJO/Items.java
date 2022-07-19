package com.example.yoyoiq.UpComingMatchPOJO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Items {
    @SerializedName("match_id")
    private int matchId;

    @SerializedName("title")
    private String title;

    @SerializedName("short_title")
    private String shortTitle;

    @SerializedName("subtitle")
    private String subtitle;

    @SerializedName("format")
    private int format;

    @SerializedName("format_str")
    private String formatStr;

    @SerializedName("status")
    private int status;

    @SerializedName("status_str")
    private String statusStr;

    @SerializedName("status_note")
    private String statusNote;

    @SerializedName("verified")
    private String verified;

    @SerializedName("pre_squad")
    private String preSquad;

    @SerializedName("odds_available")
    private String oddsAvailable;

    @SerializedName("game_state")
    private int gameState;

    @SerializedName("game_state_str")
    private String gameStateStr;

    @SerializedName("domestic")
    private String domestic;

    @SerializedName("competition")
    private ArrayList<Competition> competition;

    @SerializedName("teama")
    private ArrayList<Teama> teama;

    @SerializedName("teamb")
    private ArrayList<Teamb> teamb;

    @SerializedName("date_start")
    private String dateStart;

    @SerializedName("date_end")
    private String dateEnd;

    @SerializedName("timestamp_start")
    private int timestampStart;

    @SerializedName("timestamp_end")
    private int timestampEnd;

    @SerializedName("date_start_ist")
    private String dateStartIst;

    @SerializedName("date_end_ist")
    private String dateEndIst;

    @SerializedName("venue")
    private ArrayList<Venue> venue;

    @SerializedName("umpires")
    private String umpires;

    @SerializedName("referee")
    private String referee;

    @SerializedName("equation")
    private String equation;

    @SerializedName("live")
    private String live;

    @SerializedName("result")
    private String result;

    @SerializedName("result_type")
    private int resultType;

    @SerializedName("win_margin")
    private String winMargin;

    @SerializedName("winning_team_id")
    private int winningTeamId;

    @SerializedName("commentary")
    private int commentary;

    @SerializedName("wagon")
    private int wagon;

    @SerializedName("latest_inning_number")
    private int latestInningNumber;

    @SerializedName("presquad_time")
    private String presquadTime;

    @SerializedName("verify_time")
    private String verifyTime;

    @SerializedName("toss")
    private ArrayList<Toss> toss;

    @SerializedName("todaydate")
    private String todayDate;

    public Items(int matchId, String title, String shortTitle, String subtitle, int format, String formatStr, int status, String statusStr, String statusNote, String verified, String preSquad, String oddsAvailable, int gameState, String gameStateStr, String domestic, ArrayList<Competition> competition, ArrayList<Teama> teama, ArrayList<Teamb> teamb, String dateStart, String dateEnd, int timestampStart, int timestampEnd, String dateStartIst, String dateEndIst, ArrayList<Venue> venue, String umpires, String referee, String equation, String live, String result, int resultType, String winMargin, int winningTeamId, int commentary, int wagon, int latestInningNumber, String presquadTime, String verifyTime, ArrayList<Toss> toss, String todayDate) {
        this.matchId = matchId;
        this.title = title;
        this.shortTitle = shortTitle;
        this.subtitle = subtitle;
        this.format = format;
        this.formatStr = formatStr;
        this.status = status;
        this.statusStr = statusStr;
        this.statusNote = statusNote;
        this.verified = verified;
        this.preSquad = preSquad;
        this.oddsAvailable = oddsAvailable;
        this.gameState = gameState;
        this.gameStateStr = gameStateStr;
        this.domestic = domestic;
        this.competition = competition;
        this.teama = teama;
        this.teamb = teamb;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.timestampStart = timestampStart;
        this.timestampEnd = timestampEnd;
        this.dateStartIst = dateStartIst;
        this.dateEndIst = dateEndIst;
        this.venue = venue;
        this.umpires = umpires;
        this.referee = referee;
        this.equation = equation;
        this.live = live;
        this.result = result;
        this.resultType = resultType;
        this.winMargin = winMargin;
        this.winningTeamId = winningTeamId;
        this.commentary = commentary;
        this.wagon = wagon;
        this.latestInningNumber = latestInningNumber;
        this.presquadTime = presquadTime;
        this.verifyTime = verifyTime;
        this.toss = toss;
        this.todayDate = todayDate;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public String getFormatStr() {
        return formatStr;
    }

    public void setFormatStr(String formatStr) {
        this.formatStr = formatStr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getStatusNote() {
        return statusNote;
    }

    public void setStatusNote(String statusNote) {
        this.statusNote = statusNote;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getPreSquad() {
        return preSquad;
    }

    public void setPreSquad(String preSquad) {
        this.preSquad = preSquad;
    }

    public String getOddsAvailable() {
        return oddsAvailable;
    }

    public void setOddsAvailable(String oddsAvailable) {
        this.oddsAvailable = oddsAvailable;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public String getGameStateStr() {
        return gameStateStr;
    }

    public void setGameStateStr(String gameStateStr) {
        this.gameStateStr = gameStateStr;
    }

    public String getDomestic() {
        return domestic;
    }

    public void setDomestic(String domestic) {
        this.domestic = domestic;
    }

    public ArrayList<Competition> getCompetition() {
        return competition;
    }

    public void setCompetition(ArrayList<Competition> competition) {
        this.competition = competition;
    }

    public ArrayList<Teama> getTeama() {
        return teama;
    }

    public void setTeama(ArrayList<Teama> teama) {
        this.teama = teama;
    }

    public ArrayList<Teamb> getTeamb() {
        return teamb;
    }

    public void setTeamb(ArrayList<Teamb> teamb) {
        this.teamb = teamb;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getTimestampStart() {
        return timestampStart;
    }

    public void setTimestampStart(int timestampStart) {
        this.timestampStart = timestampStart;
    }

    public int getTimestampEnd() {
        return timestampEnd;
    }

    public void setTimestampEnd(int timestampEnd) {
        this.timestampEnd = timestampEnd;
    }

    public String getDateStartIst() {
        return dateStartIst;
    }

    public void setDateStartIst(String dateStartIst) {
        this.dateStartIst = dateStartIst;
    }

    public String getDateEndIst() {
        return dateEndIst;
    }

    public void setDateEndIst(String dateEndIst) {
        this.dateEndIst = dateEndIst;
    }

    public ArrayList<Venue> getVenue() {
        return venue;
    }

    public void setVenue(ArrayList<Venue> venue) {
        this.venue = venue;
    }

    public String getUmpires() {
        return umpires;
    }

    public void setUmpires(String umpires) {
        this.umpires = umpires;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getResultType() {
        return resultType;
    }

    public void setResultType(int resultType) {
        this.resultType = resultType;
    }

    public String getWinMargin() {
        return winMargin;
    }

    public void setWinMargin(String winMargin) {
        this.winMargin = winMargin;
    }

    public int getWinningTeamId() {
        return winningTeamId;
    }

    public void setWinningTeamId(int winningTeamId) {
        this.winningTeamId = winningTeamId;
    }

    public int getCommentary() {
        return commentary;
    }

    public void setCommentary(int commentary) {
        this.commentary = commentary;
    }

    public int getWagon() {
        return wagon;
    }

    public void setWagon(int wagon) {
        this.wagon = wagon;
    }

    public int getLatestInningNumber() {
        return latestInningNumber;
    }

    public void setLatestInningNumber(int latestInningNumber) {
        this.latestInningNumber = latestInningNumber;
    }

    public String getPresquadTime() {
        return presquadTime;
    }

    public void setPresquadTime(String presquadTime) {
        this.presquadTime = presquadTime;
    }

    public String getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
    }

    public ArrayList<Toss> getToss() {
        return toss;
    }

    public void setToss(ArrayList<Toss> toss) {
        this.toss = toss;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }
}
