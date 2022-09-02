package com.example.yoyoiq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.yoyoiq.Adapter.BannerAdapter;
import com.example.yoyoiq.Adapter.FragmentAdapter;
import com.example.yoyoiq.BannerPOJO.Banner;
import com.example.yoyoiq.Fragment.CricketFragment;
import com.example.yoyoiq.Model.The_Slide_Items_Model_Class;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ViewPager view_bannerItem;
    TabLayout tabLayout;
    View myFragment;
    ArrayList<The_Slide_Items_Model_Class> listItems = new ArrayList<>();

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
        setAutoSliderBanner();
        if (getArguments() != null) {
        }
    }

    BannerAdapter bannerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = myFragment.findViewById(R.id.recyclerViewMatchList);
        view_bannerItem = myFragment.findViewById(R.id.view_bannerItem);
        tabLayout = myFragment.findViewById(R.id.tabLayout);
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
        Call<Banner> call = ApiClient.getInstance().getApi().getBanner();
        call.enqueue(new Callback<Banner>() {
            @Override
            public void onResponse(Call<Banner> call, Response<Banner> response) {
                Banner banner = response.body();
                if (response.isSuccessful()) {
                    String bannerData = new Gson().toJson(banner.getResponse());
                    try {
                        JSONArray jsonArray = new JSONArray(bannerData);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String id = jsonObject.getString("id");
                            String image = jsonObject.getString("image");
                            The_Slide_Items_Model_Class the_slide_items_model_class = new The_Slide_Items_Model_Class(image);
                            listItems.add(the_slide_items_model_class);
                            bannerAdapter = new BannerAdapter(getContext(), listItems);
                            view_bannerItem.setAdapter(bannerAdapter);
                            bannerAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<Banner> call, Throwable t) {

            }
        });
    }
}