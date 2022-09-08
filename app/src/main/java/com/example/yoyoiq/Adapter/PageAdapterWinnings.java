package com.example.yoyoiq.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.yoyoiq.InSideAddCashLeaderboard.LeaderboardFragment;
import com.example.yoyoiq.InSideAddCashLeaderboard.WinningFragment;

public class PageAdapterWinnings extends FragmentPagerAdapter {
    int tabCount;
    String total_prize = "";
    String entryFee = "";
    String totalSports = "";
    String leftSports = "";
    String winningPer = "";
    String upTo = "";
    String match_id = "";
    String first_price = "";
    String price_contribution = "";
    String contestId = "";
    String matchA = "";
    String matchB = "";

    public PageAdapterWinnings(@NonNull FragmentManager fm, int behavior, String total_prize, String entryFee, String totalSports, String leftSports, String winningPer, String upTo, String match_id, String first_price, String price_contribution, String contestId,String matchA,String matchB) {
        super(fm, behavior);
        tabCount = behavior;
        this.total_prize = total_prize;
        this.entryFee = entryFee;
        this.totalSports = totalSports;
        this.leftSports = leftSports;
        this.winningPer = winningPer;
        this.upTo = upTo;
        this.match_id = match_id;
        this.first_price = first_price;
        this.price_contribution = price_contribution;
        this.contestId = contestId;
        this.matchA = matchA;
        this.matchB = matchB;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Bundle bundle = new Bundle();
                bundle.putString("price_contribution", price_contribution);
                WinningFragment winningFragment = new WinningFragment();
                winningFragment.setArguments(bundle);
                return winningFragment;
            case 1:
                Bundle bundle1 = new Bundle();
                bundle1.putString("match_id", match_id);
                bundle1.putString("contestId", contestId);
                bundle1.putString("matchA", matchA);
                bundle1.putString("matchB", matchB);
                LeaderboardFragment leaderboardFragment = new LeaderboardFragment();
                leaderboardFragment.setArguments(bundle1);
                return leaderboardFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}