package com.example.yoyoiq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.yoyoiq.Adapter.AllMatchAdapter;
import com.example.yoyoiq.Adapter.BannerAdapter;
import com.example.yoyoiq.Adapter.FragmentAdapter;
import com.example.yoyoiq.Fragment.CricketFragment;
import com.example.yoyoiq.Modal.The_Slide_Items_Model_Class;
import com.example.yoyoiq.Modal.TotalHomeData;
import com.example.yoyoiq.POJO.MatchListResponse;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<TotalHomeData> list = new ArrayList<>();
    ViewPager view_bannerItem;
    TabLayout tabLayout;
    View myFragment;
    private List<The_Slide_Items_Model_Class> listItems;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getAllMatches();
//        setAutoSliderBanner();
        if (getArguments() != null) {
        }
    }

    BannerAdapter bannerAdapter;
    AllMatchAdapter allMatchAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = myFragment.findViewById(R.id.recyclerViewMatchList);
        view_bannerItem = myFragment.findViewById(R.id.view_bannerItem);
        tabLayout = myFragment.findViewById(R.id.tabLayout);
        view_bannerItem.setAdapter(bannerAdapter);
        return myFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewPager(view_bannerItem);
        tabLayout.setupWithViewPager(view_bannerItem);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setUpViewPager(ViewPager view_bannerItem) {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager());
        fragmentAdapter.addFragment(new CricketFragment(), "Cricket");
        view_bannerItem.setAdapter(fragmentAdapter);
    }


    private void setAutoSliderBanner() {
        listItems = new ArrayList<>();
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.group1));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.group2));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.group3));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.group4));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.group5));
        bannerAdapter = new BannerAdapter(getContext(), listItems);
        bannerAdapter.notifyDataSetChanged();
    }

    private void getAllMatches() {
        Call<MatchListResponse> call = ApiClient
                .getInstance()
                .getApi()
                .getMatchList();

        call.enqueue(new Callback<MatchListResponse>() {
            @Override
            public void onResponse(Call<MatchListResponse> call, Response<MatchListResponse> response) {
                MatchListResponse matchList = response.body();
                if (response.isSuccessful()) {
                    matchList.getResponse();
                    String jsonArray = new Gson().toJson(matchList.getResponse().getItems());
                    JSONArray jsonArray1 = null;
                    try {
                        jsonArray1 = new JSONArray(jsonArray);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray1.getJSONObject(i);
                            String title = jsonObject.getString("title");

                            String teama = jsonObject.getString("teama");
                            String teamb = jsonObject.getString("teamb");

                            JSONObject jsonObject1 = new JSONObject(teama);
                            String logo_url_a = jsonObject1.getString("logo_url");
                            String name_a = jsonObject1.getString("name");
                            String short_name_a = jsonObject1.getString("short_name");
                            int teamIda = Integer.parseInt(jsonObject1.getString("team_id"));

                            JSONObject jsonObject2 = new JSONObject(teamb);
                            String logo_url_b = jsonObject2.getString("logo_url");
                            String name_b = jsonObject2.getString("name");
                            String short_name_b = jsonObject2.getString("short_name");
                            int teamIdb = Integer.parseInt(jsonObject1.getString("team_id"));

                            TotalHomeData totalHomeData = new TotalHomeData(title, logo_url_a, name_a, short_name_a, logo_url_b, name_b, short_name_b);
                            list.add(totalHomeData);

                            allMatchAdapter = new AllMatchAdapter(requireContext(), list);
                            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                            recyclerView.setAdapter(allMatchAdapter);
                            allMatchAdapter.notifyDataSetChanged();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                }
            }

            @Override
            public void onFailure(Call<MatchListResponse> call, Throwable t) {
            }
        });
    }
}