package com.example.yoyoiq.OnlyMyContestPOJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMyContest {
    @SerializedName("contest")
    private List<JoinContest> contest;

    @SerializedName("team")
    private List<JoinTeamDetails> team;

    public ResponseMyContest(List<JoinContest> contest, List<JoinTeamDetails> team) {
        this.contest = contest;
        this.team = team;
    }

    public List<JoinContest> getContest() {
        return contest;
    }

    public void setContest(List<JoinContest> contest) {
        this.contest = contest;
    }

    public List<JoinTeamDetails> getTeam() {
        return team;
    }

    public void setTeam(List<JoinTeamDetails> team) {
        this.team = team;
    }
}
