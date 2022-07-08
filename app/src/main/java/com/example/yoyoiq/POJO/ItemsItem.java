package com.example.yoyoiq.POJO;

import com.google.gson.annotations.SerializedName;

public class ItemsItem{

	@SerializedName("status_note")
	private String statusNote;

	@SerializedName("date_start_ist")
	private String dateStartIst;

	@SerializedName("venue")
	private Venue venue;

	@SerializedName("latest_inning_number")
	private int latestInningNumber;

	@SerializedName("presquad_time")
	private String presquadTime;

	@SerializedName("win_margin")
	private String winMargin;

	@SerializedName("wagon")
	private int wagon;

	@SerializedName("game_state_str")
	private String gameStateStr;

	@SerializedName("competition")
	private Competition competition;

	@SerializedName("title")
	private String title;

	@SerializedName("referee")
	private String referee;

	@SerializedName("winning_team_id")
	private int winningTeamId;

	@SerializedName("verify_time")
	private String verifyTime;

	@SerializedName("date_end_ist")
	private String dateEndIst;

	@SerializedName("timestamp_start")
	private int timestampStart;

	@SerializedName("domestic")
	private String domestic;

	@SerializedName("result")
	private String result;

	@SerializedName("short_title")
	private String shortTitle;

	@SerializedName("odds_available")
	private String oddsAvailable;

	@SerializedName("game_state")
	private int gameState;

	@SerializedName("timestamp_end")
	private int timestampEnd;

	@SerializedName("live")
	private String live;

	@SerializedName("result_type")
	private int resultType;

	@SerializedName("match_id")
	private int matchId;

	@SerializedName("equation")
	private String equation;

	@SerializedName("format")
	private int format;

	@SerializedName("verified")
	private String verified;

	@SerializedName("status_str")
	private String statusStr;

	@SerializedName("date_end")
	private String dateEnd;

	@SerializedName("toss")
	private Toss toss;

	@SerializedName("pre_squad")
	private String preSquad;

	@SerializedName("date_start")
	private String dateStart;

	@SerializedName("teama")
	private Teama teama;

	@SerializedName("subtitle")
	private String subtitle;

	@SerializedName("teamb")
	private Teamb teamb;

	@SerializedName("umpires")
	private String umpires;

	@SerializedName("format_str")
	private String formatStr;

	@SerializedName("commentary")
	private int commentary;

	@SerializedName("status")
	private int status;

	public ItemsItem(String title) {
	}

	public void setStatusNote(String statusNote){
		this.statusNote = statusNote;
	}

	public String getStatusNote(){
		return statusNote;
	}

	public void setDateStartIst(String dateStartIst){
		this.dateStartIst = dateStartIst;
	}

	public String getDateStartIst(){
		return dateStartIst;
	}

	public void setVenue(Venue venue){
		this.venue = venue;
	}

	public Venue getVenue(){
		return venue;
	}

	public void setLatestInningNumber(int latestInningNumber){
		this.latestInningNumber = latestInningNumber;
	}

	public int getLatestInningNumber(){
		return latestInningNumber;
	}

	public void setPresquadTime(String presquadTime){
		this.presquadTime = presquadTime;
	}

	public String getPresquadTime(){
		return presquadTime;
	}

	public void setWinMargin(String winMargin){
		this.winMargin = winMargin;
	}

	public String getWinMargin(){
		return winMargin;
	}

	public void setWagon(int wagon){
		this.wagon = wagon;
	}

	public int getWagon(){
		return wagon;
	}

	public void setGameStateStr(String gameStateStr){
		this.gameStateStr = gameStateStr;
	}

	public String getGameStateStr(){
		return gameStateStr;
	}

	public void setCompetition(Competition competition){
		this.competition = competition;
	}

	public Competition getCompetition(){
		return competition;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setReferee(String referee){
		this.referee = referee;
	}

	public String getReferee(){
		return referee;
	}

	public void setWinningTeamId(int winningTeamId){
		this.winningTeamId = winningTeamId;
	}

	public int getWinningTeamId(){
		return winningTeamId;
	}

	public void setVerifyTime(String verifyTime){
		this.verifyTime = verifyTime;
	}

	public String getVerifyTime(){
		return verifyTime;
	}

	public void setDateEndIst(String dateEndIst){
		this.dateEndIst = dateEndIst;
	}

	public String getDateEndIst(){
		return dateEndIst;
	}

	public void setTimestampStart(int timestampStart){
		this.timestampStart = timestampStart;
	}

	public int getTimestampStart(){
		return timestampStart;
	}

	public void setDomestic(String domestic){
		this.domestic = domestic;
	}

	public String getDomestic(){
		return domestic;
	}

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setShortTitle(String shortTitle){
		this.shortTitle = shortTitle;
	}

	public String getShortTitle(){
		return shortTitle;
	}

	public void setOddsAvailable(String oddsAvailable){
		this.oddsAvailable = oddsAvailable;
	}

	public String getOddsAvailable(){
		return oddsAvailable;
	}

	public void setGameState(int gameState){
		this.gameState = gameState;
	}

	public int getGameState(){
		return gameState;
	}

	public void setTimestampEnd(int timestampEnd){
		this.timestampEnd = timestampEnd;
	}

	public int getTimestampEnd(){
		return timestampEnd;
	}

	public void setLive(String live){
		this.live = live;
	}

	public String getLive(){
		return live;
	}

	public void setResultType(int resultType){
		this.resultType = resultType;
	}

	public int getResultType(){
		return resultType;
	}

	public void setMatchId(int matchId){
		this.matchId = matchId;
	}

	public int getMatchId(){
		return matchId;
	}

	public void setEquation(String equation){
		this.equation = equation;
	}

	public String getEquation(){
		return equation;
	}

	public void setFormat(int format){
		this.format = format;
	}

	public int getFormat(){
		return format;
	}

	public void setVerified(String verified){
		this.verified = verified;
	}

	public String getVerified(){
		return verified;
	}

	public void setStatusStr(String statusStr){
		this.statusStr = statusStr;
	}

	public String getStatusStr(){
		return statusStr;
	}

	public void setDateEnd(String dateEnd){
		this.dateEnd = dateEnd;
	}

	public String getDateEnd(){
		return dateEnd;
	}

	public void setToss(Toss toss){
		this.toss = toss;
	}

	public Toss getToss(){
		return toss;
	}

	public void setPreSquad(String preSquad){
		this.preSquad = preSquad;
	}

	public String getPreSquad(){
		return preSquad;
	}

	public void setDateStart(String dateStart){
		this.dateStart = dateStart;
	}

	public String getDateStart(){
		return dateStart;
	}

	public void setTeama(Teama teama){
		this.teama = teama;
	}

	public Teama getTeama(){
		return teama;
	}

	public void setSubtitle(String subtitle){
		this.subtitle = subtitle;
	}

	public String getSubtitle(){
		return subtitle;
	}

	public void setTeamb(Teamb teamb){
		this.teamb = teamb;
	}

	public Teamb getTeamb(){
		return teamb;
	}

	public void setUmpires(String umpires){
		this.umpires = umpires;
	}

	public String getUmpires(){
		return umpires;
	}

	public void setFormatStr(String formatStr){
		this.formatStr = formatStr;
	}

	public String getFormatStr(){
		return formatStr;
	}

	public void setCommentary(int commentary){
		this.commentary = commentary;
	}

	public int getCommentary(){
		return commentary;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}