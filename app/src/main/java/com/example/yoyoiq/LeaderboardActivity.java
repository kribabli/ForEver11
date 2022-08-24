package com.example.yoyoiq;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.yoyoiq.Adapter.PageAdapterLeaderboard;
import com.example.yoyoiq.common.SessionManager;
import com.google.android.material.tabs.TabLayout;

public class LeaderboardActivity extends AppCompatActivity {
    PageAdapterLeaderboard pageAdapterLeaderboard;
    ViewPager viewPager;
    TabLayout tabLayout;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        initMethod();
        setAction();

        pageAdapterLeaderboard = new PageAdapterLeaderboard(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapterLeaderboard);
        sessionManager = new SessionManager(getApplicationContext());

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    pageAdapterLeaderboard.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    pageAdapterLeaderboard.notifyDataSetChanged();
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

    }

    private void initMethod() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
    }

    private void setAction() {
    }
}