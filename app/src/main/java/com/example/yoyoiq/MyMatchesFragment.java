package com.example.yoyoiq;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.yoyoiq.Adapter.PagerAdapterLiveCompleted;
import com.example.yoyoiq.InSideMyMatches.CompletedMatchFragment;
import com.example.yoyoiq.InSideMyMatches.LiveMatchFragment;
import com.example.yoyoiq.InSideMyMatches.UpcomingMatchFragment;
import com.google.android.material.tabs.TabLayout;

public class MyMatchesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ViewPager viewPager;
    TabLayout tabLayout;
    Fragment selectedFragment = null;
    Fragment fragment = new Fragment();
    PagerAdapterLiveCompleted pagerAdapterLiveCompleted;
    private String mParam1;
    private String mParam2;

    public MyMatchesFragment() {
    }

    public static MyMatchesFragment newInstance(String param1, String param2) {
        MyMatchesFragment fragment = new MyMatchesFragment();
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

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_my_matches, container, false);
        viewPager = root.findViewById(R.id.viewPager);
        tabLayout = root.findViewById(R.id.tabLayout);
        selectedFragment = fragment;
        pagerAdapterLiveCompleted = new PagerAdapterLiveCompleted(getFragmentManager(), tabLayout.getTabCount());

        if (viewPager.getAdapter() == null) {
            viewPager.setAdapter(pagerAdapterLiveCompleted);
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    selectedFragment = new UpcomingMatchFragment();
                    pagerAdapterLiveCompleted.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    selectedFragment = new LiveMatchFragment();
                    pagerAdapterLiveCompleted.notifyDataSetChanged();
                } else if (tab.getPosition() == 2) {
                    selectedFragment = new CompletedMatchFragment();
                    pagerAdapterLiveCompleted.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPager.setAdapter(pagerAdapterLiveCompleted);
            }
        }, 500);
    }
}