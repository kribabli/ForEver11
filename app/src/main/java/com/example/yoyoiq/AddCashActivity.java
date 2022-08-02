package com.example.yoyoiq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.yoyoiq.Adapter.PageAdapterWinnings;
import com.example.yoyoiq.WalletPackage.AddCash;
import com.google.android.material.tabs.TabLayout;

public class AddCashActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    LinearLayout linearLayout,joinLinearLayout;
    PageAdapterWinnings pageAdapterWinnings;
    String total_prize, entryFee, totalSports, leftSports, winningPer, upTo, matchA, matchB, match_id, first_price, price_contribution;
    TextView backPress, teamATv, teamBTv, walletTV;
    TextView total_prize1, entryFee1, totalSports1, leftSports1, winningPer1, upTo1, first_price1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cash);
        initMethod();
        setAction();

        pageAdapterWinnings = new PageAdapterWinnings(getSupportFragmentManager(), tabLayout.getTabCount(), total_prize, entryFee, totalSports, leftSports, winningPer, upTo, match_id, first_price, price_contribution);
        viewPager.setAdapter(pageAdapterWinnings);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    linearLayout.setVisibility(View.VISIBLE);
                    pageAdapterWinnings.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    linearLayout.setVisibility(View.GONE);
                    pageAdapterWinnings.notifyDataSetChanged();
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
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        linearLayout = findViewById(R.id.linerLayout1);
        joinLinearLayout = findViewById(R.id.joinLinearLayout);

        backPress = findViewById(R.id.backPress);
        teamATv = findViewById(R.id.matchATv);
        teamBTv = findViewById(R.id.matchBTv);
        walletTV = findViewById(R.id.walletTV);
        total_prize1 = findViewById(R.id.total_prize);
        first_price1 = findViewById(R.id.first_price);
        entryFee1 = findViewById(R.id.joinFee);
        totalSports1 = findViewById(R.id.totalSports);
        leftSports1 = findViewById(R.id.leftSports);
        winningPer1 = findViewById(R.id.winningPer);
        upTo1 = findViewById(R.id.upTo);

        total_prize = getIntent().getStringExtra("total_prize");
        entryFee = getIntent().getStringExtra("entryFee");
        totalSports = getIntent().getStringExtra("totalSports");
        leftSports = getIntent().getStringExtra("leftSports");
        winningPer = getIntent().getStringExtra("winningPer");
        upTo = getIntent().getStringExtra("upTo");
        matchA = getIntent().getStringExtra("matchA");
        matchB = getIntent().getStringExtra("matchB");
        match_id = getIntent().getStringExtra("match_id");
        first_price = getIntent().getStringExtra("first_price");
        price_contribution = getIntent().getStringExtra("price_contribution");

        teamATv.setText(matchA);
        teamBTv.setText(matchB);
        first_price1.setText(first_price);
        total_prize1.setText(total_prize);
        entryFee1.setText(entryFee);
        totalSports1.setText(totalSports);
        leftSports1.setText(leftSports);
        winningPer1.setText(winningPer);
        upTo1.setText(upTo);
    }

    private void setAction() {
        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        walletTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCashActivity.this, AddCash.class);
                startActivity(intent);
            }
        });

        joinLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCashActivity.this, CreateTeamActivity.class);
                startActivity(intent);
            }
        });
    }
}