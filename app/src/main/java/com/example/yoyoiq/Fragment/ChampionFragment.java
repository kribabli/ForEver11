package com.example.yoyoiq.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.yoyoiq.Adapter.AllMatchAdapter;
import com.example.yoyoiq.Adapter.BannerAdapter;
import com.example.yoyoiq.Model.The_Slide_Items_Model_Class;
import com.example.yoyoiq.Model.TotalHomeData;
import com.example.yoyoiq.R;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.example.yoyoiq.UpcommingReq.UpcommingResponse;
import com.example.yoyoiq.common.HelperData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChampionFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    ViewPager view_bannerItem;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<TotalHomeData> list = new ArrayList<>();
    private List<The_Slide_Items_Model_Class> listItems;

    
    public ChampionFragment() {
        // Required empty public constructor
    }

    public static ChampionFragment newInstance(String param1, String param2) {
        ChampionFragment fragment = new ChampionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAllMatches();
        setAutoSliderBanner();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    BannerAdapter bannerAdapter;
    AllMatchAdapter allMatchAdapter1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_champion, container, false);
        view_bannerItem = root.findViewById(R.id.view_bannerItem);
        swipeRefreshLayout = root.findViewById(R.id.swiper);
        recyclerView = root.findViewById(R.id.recyclerViewMatchList);
        view_bannerItem.setAdapter(bannerAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllMatches();
            }
        });
        HelperData.limit1 = 4;
        return root;
    }

    private void setAutoSliderBanner() {
        listItems = new ArrayList<>();
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.banner2));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.banner3));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.banner4));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.banner5));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.banner6));
        bannerAdapter = new BannerAdapter(getContext(), listItems);
        bannerAdapter.notifyDataSetChanged();
    }

    private void getAllMatches() {
        list.clear();
        Call<UpcommingResponse> call = ApiClient
                .getInstance()
                .getApi()
                .getMatch();

        call.enqueue(new Callback<UpcommingResponse>() {
            @Override
            public void onResponse(Call<UpcommingResponse> call, Response<UpcommingResponse> response) {
                UpcommingResponse status = response.body();
                if (response.isSuccessful()) {
                    String jsonArray = new Gson().toJson(status.getResponse().getItems());
                    JSONArray jsonArray1 = null;
                    try {
                        jsonArray1 = new JSONArray(jsonArray);
                        for (int i = 0; i < jsonArray1.length(); i++) {
                            JSONObject jsonObject = jsonArray1.getJSONObject(i);
                            String title = jsonObject.getString("title");
                            String match_id = jsonObject.getString("match_id");
                            String date_start = jsonObject.getString("date_start_ist");
                            String date_end = jsonObject.getString("date_end_ist");

                            JSONArray teama1 = jsonObject.getJSONArray("teama");
                            JSONArray teamb1 = jsonObject.getJSONArray("teamb");

                            JSONObject jsonObject11 = teama1.getJSONObject(0);
                            JSONObject jsonObject22 = teamb1.getJSONObject(0);

                            String logo_url_a = jsonObject11.getString("logo_url");
                            String name_a = jsonObject11.getString("name");
                            String short_name_a = jsonObject11.getString("short_name");
                            int teamIda = Integer.parseInt(jsonObject11.getString("team_id"));

                            String logo_url_b = jsonObject22.getString("logo_url");
                            String name_b = jsonObject22.getString("name");
                            String short_name_b = jsonObject22.getString("short_name");
                            int teamIdb = Integer.parseInt(jsonObject22.getString("team_id"));

                            TotalHomeData totalHomeData = new TotalHomeData(title, match_id, logo_url_a, name_a, short_name_a, logo_url_b, name_b, short_name_b, date_start, date_end, 4);
                            list.add(totalHomeData);

                            allMatchAdapter1 = new AllMatchAdapter(getContext(), list);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(allMatchAdapter1);
                            allMatchAdapter1.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    } catch (JSONException e) {
                        swipeRefreshLayout.setRefreshing(false);
                        e.printStackTrace();
                    }

                } else {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<UpcommingResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}