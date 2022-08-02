package com.example.yoyoiq.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.yoyoiq.Fragment.ContestsFragment;
import com.example.yoyoiq.Fragment.LeaderboardFragment;
import com.example.yoyoiq.Fragment.MyContestsFragment;
import com.example.yoyoiq.Fragment.WinningFragment;

public class PageAdapterWinnings extends FragmentPagerAdapter {
    int tabCount;
    String total_prize = "";
    String entryFee = "";
    String totalSports = "";
    String leftSports = "";
    String winningPer = "";
    String upTo = "";

    public PageAdapterWinnings(@NonNull FragmentManager fm, int behavior, String total_prize, String entryFee, String totalSports, String leftSports, String winningPer, String upTo) {
        super(fm, behavior);
        tabCount = behavior;
        this.total_prize = total_prize;
        this.entryFee = entryFee;
        this.totalSports = totalSports;
        this.leftSports = leftSports;
        this.winningPer = winningPer;
        this.upTo = upTo;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Bundle bundle = new Bundle();
                bundle.putString("total_prize", total_prize);
                bundle.putString("entryFee", entryFee);
                bundle.putString("totalSports", totalSports);
                bundle.putString("leftSports", leftSports);
                bundle.putString("winningPer", winningPer);
                bundle.putString("upTo", upTo);
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
