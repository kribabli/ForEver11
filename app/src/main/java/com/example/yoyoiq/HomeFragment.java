package com.example.yoyoiq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.yoyoiq.Adapter.AllMatchAdapter;
import com.example.yoyoiq.Adapter.BannerAdapter;
import com.example.yoyoiq.Adapter.FragmentAdapter;
import com.example.yoyoiq.Fragment.CricketFragment;
import com.example.yoyoiq.Model.The_Slide_Items_Model_Class;
import com.example.yoyoiq.Model.TotalHomeData;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

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
        setAutoSliderBanner();
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

}