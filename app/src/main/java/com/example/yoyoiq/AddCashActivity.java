package com.example.yoyoiq;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.yoyoiq.Adapter.PageAdapterWinnings;
import com.google.android.material.tabs.TabLayout;

public class AddCashActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    PageAdapterWinnings pageAdapterWinnings;
    String total_prize, entryFee, totalSports, leftSports, winningPer, upTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cash);
        total_prize = getIntent().getStringExtra("total_prize");
        entryFee = getIntent().getStringExtra("entryFee");
        totalSports = getIntent().getStringExtra("totalSports");
        leftSports = getIntent().getStringExtra("leftSports");
        winningPer = getIntent().getStringExtra("winningPer");
        upTo = getIntent().getStringExtra("upTo");

        pageAdapterWinnings = new PageAdapterWinnings(getSupportFragmentManager(), tabLayout.getTabCount(), total_prize, entryFee, totalSports, leftSports, winningPer, upTo);
        viewPager.setAdapter(pageAdapterWinnings);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0 || tab.getPosition() == 1) {
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
}