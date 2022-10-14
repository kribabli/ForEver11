package com.example.yoyoiq.InSideContestActivityFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.yoyoiq.Adapter.ContestsListAdapter;
import com.example.yoyoiq.ContestPOJO.Contests;
import com.example.yoyoiq.Model.ContestsListPOJO;
import com.example.yoyoiq.R;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContestsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String match_id;
    String matchA, matchB, price_contribution;
    ArrayList<ContestsListPOJO> list = new ArrayList<>();
    RecyclerView recyclerViewContest;
    SwipeRefreshLayout swipeRefreshLayout;
    private String mParam1;
    private String mParam2;

    public ContestsFragment() {
        // Required empty public constructor
    }

    public static ContestsFragment newInstance(String param1, String param2) {
        ContestsFragment fragment = new ContestsFragment();
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

    ContestsListAdapter contestsListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_contests, container, false);
        if (getArguments() != null) {
            match_id = getArguments().getString("match_id");
        }

        recyclerViewContest = root.findViewById(R.id.contestsList);
        swipeRefreshLayout = root.findViewById(R.id.swiper);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllContests();
            }
        });

        return root;
    }

    private void getAllContests() {
        list.clear();
        matchA = getArguments().getString("matchA");
        matchB = getArguments().getString("matchB");
        match_id = getArguments().getString("match_id");

        Call<Contests> call = ApiClient
                .getInstance()
                .getApi()
                .getContestsList(getArguments().getString("match_id"));

        call.enqueue(new Callback<Contests>() {
            @Override
            public void onResponse(Call<Contests> call, Response<Contests> response) {
                Contests contests = response.body();
                if (response.isSuccessful()) {
                    String jsonArray = new Gson().toJson(contests.getResponse());

                    //----------------------for Contests----------------------------
                    JSONArray jsonArray1Contest = null;
                    try {
                        jsonArray1Contest = new JSONArray(jsonArray);
                        for (int i = 0; i < jsonArray1Contest.length(); i++) {
                            JSONObject jsonObjectContest = jsonArray1Contest.getJSONObject(i);
                            price_contribution = jsonObjectContest.getString("price_contribution");

                            String contest_id = jsonObjectContest.getString("contest_id");
                            String contest_name = jsonObjectContest.getString("contest_name");
                            String entry = jsonObjectContest.getString("entry");
                            String join_team = jsonObjectContest.getString("join_team");
                            String prize_pool = jsonObjectContest.getString("prize_pool");
                            String total_team = jsonObjectContest.getString("total_team");
                            String winners = jsonObjectContest.getString("winners");
                            String first_price = jsonObjectContest.getString("first_price");
                            String winning_percentage = jsonObjectContest.getString("winning_percentage");
                            String upto = jsonObjectContest.getString("upto");
                            String contest_description = jsonObjectContest.getString("contest_description");

                            ContestsListPOJO contestsListPOJO = new ContestsListPOJO(contest_id, contest_name, entry, join_team, prize_pool, total_team, winners, first_price, winning_percentage, upto, contest_description, matchA, matchB, match_id, price_contribution);
                            list.add(contestsListPOJO);
                            contestsListAdapter = new ContestsListAdapter(getContext(), list);
                            recyclerViewContest.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerViewContest.setAdapter(contestsListAdapter);
                            contestsListAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    } catch (Exception e) {
                        swipeRefreshLayout.setRefreshing(false);
                        e.printStackTrace();
                    }

                } else {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<Contests> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getAllContests();
        }
    }
}