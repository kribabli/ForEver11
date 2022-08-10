package com.example.yoyoiq.Retrofit;

import com.example.yoyoiq.ContestPOJO.Contests;
import com.example.yoyoiq.LoginPojo.LoginResponse;
import com.example.yoyoiq.LoginPojo.RegistrationResponse;
import com.example.yoyoiq.CreatedTeamPOJO.CreatedTeamResponse;
import com.example.yoyoiq.PlayerPOJO.ResponsePlayer;
import com.example.yoyoiq.UpcommingReq.UpcommingResponse;

import org.json.JSONObject;

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
    @POST("userTeam")
    Call<JSONObject> Send_myteam_list_Server(
            @Field("user_id") String User_id,
            @Field("match_id") String match_id,
            @Field("squads") String squads
//            @Body SendCreatedTeamServer sendCreatedTeamServer
    );

    @FormUrlEncoded
    @POST("userTeamList")
    Call<CreatedTeamResponse> getUserTeamCreated(
            @Field("user_id") String user_id,
            @Field("match_id") String match_id
    );

    @FormUrlEncoded
    @POST("registration")
    Call<RegistrationResponse> SendUserDetails_server(
            @Field("mobile_no") String mobile_no,
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("registration")
    Call<LoginResponse> getUserLoginData(
            @Field("email_or_mobile") String mobile_no,
            @Field("password") String password
    );
}