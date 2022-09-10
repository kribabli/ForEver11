package com.example.yoyoiq;

import android.os.Bundle;
import android.widget.TextView;

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
    TextView contestName, bakPress;
    String contest_description, match_id, price_contribution, contest_id, contest_name, matchA, matchB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        initMethod();
        setAction();

        pageAdapterLeaderboard = new PageAdapterLeaderboard(getSupportFragmentManager(), tabLayout.getTabCount(), price_contribution, match_id, contest_id, matchA, matchB);
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
        contestName = findViewById(R.id.contestName);
        bakPress = findViewById(R.id.backPress);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
    }

    private void setAction() {
        contest_description = getIntent().getStringExtra("contest_description");
        price_contribution = getIntent().getStringExtra("price_contribution");
        match_id = getIntent().getStringExtra("match_id");
        matchA = getIntent().getStringExtra("matchA");
        matchB = getIntent().getStringExtra("matchB");
        contest_id = getIntent().getStringExtra("contest_id");
        contest_name = getIntent().getStringExtra("contest_name");
        contestName.setText(contest_name);
        bakPress.setOnClickListener(v -> onBackPressed());
    }
}