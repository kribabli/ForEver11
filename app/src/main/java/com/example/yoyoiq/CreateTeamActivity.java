package com.example.yoyoiq;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.yoyoiq.Adapter.PageAdapterPlayer;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class CreateTeamActivity extends AppCompatActivity {
    TextView backPress;
    Button continueBtn;
    String match_id;
    ViewPager viewPager;
    TabLayout tabLayout;
    TabItem tabItem1, tabItem2, tabItem3, tabItem4;
    PageAdapterPlayer pageAdapterPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team_actvity);
        initMethod();
        setAction();

        pageAdapterPlayer = new PageAdapterPlayer(getSupportFragmentManager(), tabLayout.getTabCount(), match_id);
        viewPager.setAdapter(pageAdapterPlayer);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2 || tab.getPosition() == 3) {
                    pageAdapterPlayer.notifyDataSetChanged();
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
        continueBtn = findViewById(R.id.Continue);

        match_id = getIntent().getStringExtra("match_id");

        tabLayout = findViewById(R.id.tabLayout);
        tabItem1 = findViewById(R.id.WK);
        tabItem2 = findViewById(R.id.BAT);
        tabItem3 = findViewById(R.id.AR);
        tabItem4 = findViewById(R.id.BOWL);
        viewPager = findViewById(R.id.viewPager);
    }

    private void setAction() {
        backPress.setOnClickListener(view -> onBackPressed());
    }

}