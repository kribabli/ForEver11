package com.example.yoyoiq.InSideContestActivityFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.yoyoiq.Adapter.MyCreatedTeamAdapter;
import com.example.yoyoiq.CreatedTeamPOJO.CreatedTeamPOJOClass;
import com.example.yoyoiq.CreatedTeamPOJO.CreatedTeamResponse;
import com.example.yoyoiq.R;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTeamsFragment extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    ArrayList<CreatedTeamPOJOClass> list = new ArrayList();

    public MyTeamsFragment() {
        // Required empty public constructor
    }

    public static MyTeamsFragment newInstance(String param1, String param2) {
        MyTeamsFragment fragment = new MyTeamsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    MyCreatedTeamAdapter myCreatedTeamAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_teams, container, false);
        recyclerView = root.findViewById(R.id.myAllTeamList);
        swipeRefreshLayout = root.findViewById(R.id.swiper);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMyAllCreatedTeam();
            }
        });
        return root;
    }

    private void getMyAllCreatedTeam() {
        Call<CreatedTeamResponse> call = ApiClient
                .getInstance()
                .getApi()
                .getUserTeamCreated("71", "56659");

        call.enqueue(new Callback<CreatedTeamResponse>() {
            @Override
            public void onResponse(Call<CreatedTeamResponse> call, Response<CreatedTeamResponse> response) {
                CreatedTeamResponse createdTeamResponse = response.body();
                if (response.isSuccessful()) {
                    String totalData = new Gson().toJson(createdTeamResponse.getResponse());

                    //----------------------for CreatedTeam(Per User)----------------------------
                    JSONArray squads = null;
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(totalData);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            try {
                                squads = jsonObject.getJSONArray("squads");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        for (int j = 0; j < squads.length(); j++) {
                            JSONObject jsonObjectSquads = squads.getJSONObject(j);
                            boolean added = jsonObjectSquads.getBoolean("added");
                            String country = jsonObjectSquads.getString("country");
                            String fantasy_player_rating = jsonObjectSquads.getString("fantasy_player_rating");
                            boolean isCap = jsonObjectSquads.getBoolean("isCap");
                            boolean isVcap = jsonObjectSquads.getBoolean("isVcap");
                            String matchId = jsonObjectSquads.getString("matchId");
                            String pid = jsonObjectSquads.getString("pid");
                            String playing_role = jsonObjectSquads.getString("playing_role");
                            String points = jsonObjectSquads.getString("points");
                            String title = jsonObjectSquads.getString("title");

                            CreatedTeamPOJOClass createdTeamPOJOClass = new CreatedTeamPOJOClass(added, country, fantasy_player_rating, isCap, isVcap, matchId, pid, playing_role, points, title);
                            list.add(createdTeamPOJOClass);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(myCreatedTeamAdapter);
                            myCreatedTeamAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<CreatedTeamResponse> call, Throwable t) {

            }
        });


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getMyAllCreatedTeam();
        }
    }
}