package com.example.yoyoiq.Retrofit;

import com.example.yoyoiq.ContestPOJO.Contests;
import com.example.yoyoiq.PlayerPOJO.ResponsePlayer;
import com.example.yoyoiq.UpcommingReq.UpcommingResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsersServices {

    @GET("upcomingMatchesList")
    Call<UpcommingResponse> getMatch(
    );

    @FormUrlEncoded
    @POST("matchPlaying11detail")
    Call<ResponsePlayer> getMatchPlaying11(
            @Field("match_id") String match_id
    );

    @FormUrlEncoded
    @POST("contestList")
    Call<Contests> getContestsList(
            @Field("match_id") String match_id
    );

    @FormUrlEncoded
    @POST("userTeamContest")
    Call<Contests>Send_myteam_list_Server(
            @Field("match_id") String match_id
    );
}
