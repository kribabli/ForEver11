package com.example.yoyoiq.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.yoyoiq.InSideAddCashLeaderboard.LeaderboardFragment;
import com.example.yoyoiq.InSideAddCashLeaderboard.WinningFragment;

public class PageAdapterLeaderboard extends FragmentPagerAdapter {
    int tabCount;
//    String price_contribution = "";

    public PageAdapterLeaderboard(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
//        this.price_contribution = price_contribution;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Bundle bundle = new Bundle();
//                bundle.putString("price_contribution", price_contribution);
                WinningFragment winningFragment = new WinningFragment();
                winningFragment.setArguments(bundle);
                return winningFragment;
            case 1:
                return new LeaderboardFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}