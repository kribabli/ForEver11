package com.example.yoyoiq.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.yoyoiq.Adapter.PriceListAdapter;
import com.example.yoyoiq.ContestPOJO.Contests;
import com.example.yoyoiq.Model.PriceContributionPOJO;
import com.example.yoyoiq.R;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WinningFragment extends Fragment {
    String price_contribution;
    ArrayList<PriceContributionPOJO> listPrice = new ArrayList<>();
    RecyclerView recyclerViewContest;
    SwipeRefreshLayout swipeRefreshLayout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public WinningFragment() {
    }

    public static WinningFragment newInstance(String param1, String param2) {
        WinningFragment fragment = new WinningFragment();
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

    PriceListAdapter priceListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_winning, container, false);
        recyclerViewContest = root.findViewById(R.id.winningList);
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
        listPrice.clear();
        price_contribution = getArguments().getString("price_contribution");

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
                    JSONArray jsonArray1 = null;
                    try {
                        jsonArray1 = new JSONArray(price_contribution);
                        for (int i = 0; i < jsonArray1.length(); i++) {
                            JSONObject jsonObject = jsonArray1.getJSONObject(i);
                            String price = jsonObject.getString("position");

                            PriceContributionPOJO priceContributionPOJO = new PriceContributionPOJO(i + 1, price);
                            listPrice.add(priceContributionPOJO);
                            priceListAdapter = new PriceListAdapter(getContext(), listPrice);
                            recyclerViewContest.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerViewContest.setAdapter(priceListAdapter);
                            priceListAdapter.notifyDataSetChanged();
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