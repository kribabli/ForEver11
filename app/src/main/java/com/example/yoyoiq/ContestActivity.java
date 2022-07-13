package com.example.yoyoiq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.yoyoiq.Adapter.PageAdapter;
import com.example.yoyoiq.WalletPackage.AddCash;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ContestActivity extends AppCompatActivity {
    TextView walletTV, backPress, matchATv, matchBTv;
    String matchA = "", matchB = "";
    LinearLayout createTeamLayout;
    ViewPager viewPager;
    TabLayout tabLayout;
    TabItem tabItem1,tabItem2,tabItem3;
    PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);
        walletTV = findViewById(R.id.walletTV);
        backPress = findViewById(R.id.backPress);
        matchATv = findViewById(R.id.matchATv);
        matchBTv = findViewById(R.id.matchBTv);
        createTeamLayout = findViewById(R.id.createTeamLayout);

        matchA = getIntent().getStringExtra("shortNameA");
        matchB = getIntent().getStringExtra("shortNameB");
        matchATv.setText(matchA);
        matchBTv.setText(matchB);

        tabLayout=findViewById(R.id.tabLayout);
        tabItem1=findViewById(R.id.contests);
        tabItem2=findViewById(R.id.myContests);
        tabItem3=findViewById(R.id.myTeams);
        viewPager=findViewById(R.id.viewPager);

        pageAdapter=new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0 || tab.getPosition()==1 || tab.getPosition()==2){
                    pageAdapter.notifyDataSetChanged();
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

        walletTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContestActivity.this, AddCash.class);
                startActivity(intent);
            }
        });

        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}