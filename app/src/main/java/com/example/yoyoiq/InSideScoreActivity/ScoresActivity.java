package com.example.yoyoiq.InSideScoreActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.yoyoiq.Adapter.PageAdapterScores;
import com.example.yoyoiq.R;
import com.google.android.material.tabs.TabLayout;

public class ScoresActivity extends AppCompatActivity {
    TextView backPress, matchATv, matchBTv;
    String matchA = "", matchB = "", match_id;
    ViewPager viewPager;
    TabLayout tabLayout;
    PageAdapterScores pageAdapterScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        initMethod();
        setAction();

        pageAdapterScores = new PageAdapterScores(getSupportFragmentManager(), tabLayout.getTabCount(), match_id, matchA, matchB);
        viewPager.setAdapter(pageAdapterScores);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    pageAdapterScores.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    pageAdapterScores.notifyDataSetChanged();
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
        backPress = findViewById(R.id.backPress);
        matchATv = findViewById(R.id.matchATv);
        matchBTv = findViewById(R.id.matchBTv);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }

    private void setAction() {
        match_id = getIntent().getStringExtra("match_id");
        matchA = getIntent().getStringExtra("shortNameA");
        matchB = getIntent().getStringExtra("shortNameB");
        matchATv.setText(matchA);
        matchBTv.setText(matchB);
        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}