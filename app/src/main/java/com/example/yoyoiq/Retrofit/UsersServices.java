package com.example.yoyoiq.Retrofit;

import com.example.yoyoiq.POJO.MatchListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersServices {
    @GET("v2/matches?status=2&format=6&token=44ae21e57e9ba30194b881c2e1342dd9&per_page=28&&paged=1")
//    @GET("v2/matches?status=2&format=6&token=ec471071441bb2ac538a0ff901abd249&per_page=10&&paged=1")
    Call<MatchListResponse> getMatchList(
    );
}
