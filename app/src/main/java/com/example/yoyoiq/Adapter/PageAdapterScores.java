package com.example.yoyoiq.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.yoyoiq.InSideContestActivityFragments.MyTeamsFragment;
import com.example.yoyoiq.InSideScoreActivity.LiveMyTeamFragment;
import com.example.yoyoiq.InSideScoreActivity.MyJoinContestsFragment;

public class PageAdapterScores extends FragmentPagerAdapter {
    int tabCount;
    String match_id = "";
    String matchA = "";
    String matchB = "";

    public PageAdapterScores(@NonNull FragmentManager fm, int behavior, String match_id, String matchA, String matchB) {
        super(fm, behavior);
        tabCount = behavior;
        this.match_id = match_id;
        this.matchA = matchA;
        this.matchB = matchB;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MyJoinContestsFragment();
            case 1:
                return new LiveMyTeamFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}