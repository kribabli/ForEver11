package com.example.yoyoiq.OnlyMyContestPOJO;

import com.google.gson.annotations.SerializedName;

public class ResponseMyContest {
    @SerializedName("contest")
    private JoinContest contest;

    @SerializedName("team")
    private JoinTeamDetails team;

    public ResponseMyContest(JoinContest contest, JoinTeamDetails team) {
        this.contest = contest;
        this.team = team;
    }

    public JoinContest getContest() {
        return contest;
    }

    public void setContest(JoinContest contest) {
        this.contest = contest;
    }

    public JoinTeamDetails getTeam() {
        return team;
    }

    public void setTeam(JoinTeamDetails team) {
        this.team = team;
    }
}
