package com.example.yoyoiq.Retrofit;

import com.example.yoyoiq.PlayerPOJO.ResponsePlayer;
import com.example.yoyoiq.UpComingMatchPOJO.Status;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsersServices {

    @GET("upcomingMatchesList")
    Call<Status> getMatch(
    );

    @FormUrlEncoded
    @POST("matchPlaying11detail")
    Call<ResponsePlayer> getMatchPlaying11(
            @Field("match_id") String match_id
    );
}
