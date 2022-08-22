package com.example.yoyoiq.Retrofit;

import com.example.yoyoiq.ContestPOJO.Contests;
import com.example.yoyoiq.CreatedTeamPOJO.CreatedTeamResponse;
import com.example.yoyoiq.JoinContest.JoinContestsResponse;
import com.example.yoyoiq.KYC.KycAddedPostResponse;
import com.example.yoyoiq.LoginPojo.LoginResponse;
import com.example.yoyoiq.LoginPojo.RegistrationResponse;
import com.example.yoyoiq.PlayerPOJO.ResponsePlayer;
import com.example.yoyoiq.UpcommingReq.UpcommingResponse;
import com.example.yoyoiq.WalletPackage.ContestJoinResponse;
import com.example.yoyoiq.WalletPackage.PostBalanceResponse;
import com.example.yoyoiq.WalletPackage.ViewBalanceResponse;
import com.example.yoyoiq.WalletPackage.ViewTransactionHistoryResponse;

import org.json.JSONObject;

import okhttp3.RequestBody;
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
            @Field("squads") String squads,
            @Field("short_squads") String short_squads
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
    @POST("userlogin")
    Call<LoginResponse> getUserLoginData(
            @Field("email_or_mobile") String mobile_no,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("viewbalance")
    Call<ViewBalanceResponse> getBalanceDetails(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("viewkycdetail")
    Call<JSONObject> getkycDetails(
            @Field("user_id") String user_id

    );

    @FormUrlEncoded
    @POST("viewtransection")
    Call<ViewTransactionHistoryResponse> getTransactionDetails(
            @Field("user_id") String user_id

    );

    @FormUrlEncoded
    @POST("myContestMatchesList")
    Call<UpcommingResponse> getContestMatchesList(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("liveContestMatchesList")
    Call<UpcommingResponse> getLiveContestMatchesList(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("addbalance")
    Call<PostBalanceResponse> sendBalanceData(
            @Field("user_id") String user_id,
            @Field("balance") String balance,
            @Field("transection_id") String transaction_id
    );

    @FormUrlEncoded
    @POST("joinContest")
    Call<JoinContestsResponse> getJoinContestResponse(
            @Field("user_id") String user_id,
            @Field("match_id") String match_id,
            @Field("contest_id") String contest_id,
            @Field("team_id") String team_id
    );

    @FormUrlEncoded
    @POST("addkycdetail")
    Call<KycAddedPostResponse> sendKycDetailsOnServer(
            @Field("user_id") RequestBody userid,
            @Field("fullname") RequestBody fullName,
            @Field("account_no") RequestBody accountNo,
            @Field("ifsc_code") RequestBody ifsc,
            @Field("bank_name") RequestBody bankName,
            @Field("dob") RequestBody dob,
            @Field("address") RequestBody address,
            @Field("adhar_no") RequestBody adhar_no,
            @Field("pancard_no") RequestBody pan,
            @Field("pancard") RequestBody pancard
    );


    @FormUrlEncoded
    @POST("contestJoinFee")
    Call<ContestJoinResponse>requestToJoinFee(
            @Field("user_id") String user_id,
            @Field("contest_id") String match_id,
            @Field("amount") String contest_id
    );
}